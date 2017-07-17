package Controller;

import Db.ClaimLocation;
import Db.ClaimStatus;
import Db.DamageType;
import Db.EvidencePhoto;
import Db.InsuranceAgent;
import Db.InsuranceClaim;
import Db.InsuranceCoverage;
import Db.InsuredVehical;
import Db.VehicalOwner;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;

public class Claim {

    private static SessionFactory sf;
    private final Session ses;
    private Transaction tr;
    private final SimpleDateFormat dateFormat;
    private final SimpleDateFormat timeFormat;
    private final SimpleDateFormat dateTimeFormat;
    private final Serializable STATUS_CLAIMED = 1;

    public Claim() {
        if (sf == null) {
            sf = Controller.Connection.getSessionFactory();
        }
        ses = sf.openSession();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

    }

    public String reportClaim(String vehicle, String damage, String city, String longitude, String latitude, String description, String filePath) throws Exception {
        tr = ses.beginTransaction();
        try {
            Db.InsuranceClaim claim = new Db.InsuranceClaim();

            claim.setClaimDatetime(new Date());
            claim.setClaimDescription(description);

            //Set Location
            Db.ClaimLocation location = new Db.ClaimLocation();

            location.setLogitude(Double.parseDouble(longitude));
            location.setLatitude(Double.parseDouble(latitude));
            location.setCity(new City().getCityByName(city));

            //Save Location
            ses.save(location);

            claim.setClaimLocation(location);
            //End Set Location

            claim.setClaimStatus((ClaimStatus) ses.load(Db.ClaimStatus.class, STATUS_CLAIMED));
            claim.setDamageType((DamageType) ses.load(Db.DamageType.class, Integer.parseInt(damage)));
            claim.setInsuredVehical((InsuredVehical) ses.load(Db.InsuredVehical.class, vehicle));

            //Save Claim
            ses.save(claim);

            EvidencePhoto photo = new EvidencePhoto();

            photo.setEvidenceImageUrl(filePath);
            photo.setInsuranceClaim(claim);

            //Save Evidence
            ses.save(photo);

            tr.commit();

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public String initClaim(HttpServletRequest request) {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                List<FileItem> items = upload.parseRequest(request);

                String vehicle = null, damage = null, city = null, longitude = null, latitude = null, description = null, filePath = null;

                for (FileItem fileItem : items) {
                    if (!fileItem.isFormField()) {
                        String name = new File(fileItem.getName()).getName();
                        String saveName = "evidence\\video\\" + System.currentTimeMillis() + "_" + name;
                        fileItem.write(new File(request.getServletContext().getRealPath("/") + saveName));
                        filePath = saveName;
                        System.out.println("SAVED:" + saveName);
                    } else {
                        switch (fileItem.getFieldName()) {
                            case "vehicle":
                                vehicle = fileItem.getString();
                                break;
                            case "damage":
                                damage = fileItem.getString();
                                break;
                            case "city":
                                city = fileItem.getString();
                                break;
                            case "longitude":
                                longitude = fileItem.getString();
                                break;
                            case "latitude":
                                latitude = fileItem.getString();
                                break;
                            case "description":
                                description = fileItem.getString();
                                break;
                        }
                    }
                }

                if (vehicle != null & damage != null & city != null & longitude != null & latitude != null & description != null) {
                    return reportClaim(vehicle, damage, city, longitude, latitude, description, filePath);
                } else {
                    return "BAD_DATA";
                }
            } else {
                return "BAD_DATA";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public String getClaims() {
        JSONArray json = new JSONArray();
        Map data = new HashMap();
        try {
            Criteria cr = ses.createCriteria(InsuranceClaim.class);
            cr.add(Restrictions.eq("claimStatus", ses.load(ClaimStatus.class, STATUS_CLAIMED)));
            cr.addOrder(Order.desc("claimDatetime"));
            List<InsuranceClaim> db = cr.list();
            List claims = new ArrayList();

            for (InsuranceClaim claim : db) {
                Map c = new HashMap();

                c.put("date", dateFormat.format(claim.getClaimDatetime()));
                c.put("time", timeFormat.format(claim.getClaimDatetime()));
                c.put("description", claim.getClaimDescription());
                ClaimLocation location = claim.getClaimLocation();
                c.put("city", location.getCity().getCity());
                c.put("longitude", location.getLogitude());
                c.put("latitude", location.getLatitude());
                c.put("status", claim.getClaimStatus().getClaimStatus());
                c.put("damage_type", claim.getDamageType().getDamageType());
                if (claim.getEvidencePhotos() != null) {
                    if (!claim.getEvidencePhotos().isEmpty()) {
                        EvidencePhoto video = (EvidencePhoto) claim.getEvidencePhotos().toArray()[0];
                        c.put("evidence", video.getEvidenceImageUrl());
                    }
                }
                if (claim.getInsuranceAgent() != null) {
                    c.put("agent", claim.getInsuranceAgent().getAgentNic());
                } else {
                    c.put("agent", "NA");
                }

                c.put("id", claim.getInsuranceClaimId());
                InsuredVehical vehicle = claim.getInsuredVehical();
                c.put("vehicle_id", vehicle.getInsuredVehicalId());
                c.put("vehicle_brand", vehicle.getVehicalModel().getVehicalBrand().getVehicalBrand());
                c.put("vehicle_model", vehicle.getVehicalModel().getVehicalModel());
                c.put("vehicle_year", vehicle.getYearOfMade());
                c.put("owner", vehicle.getVehicalOwner().getFirstName() + " " + vehicle.getVehicalOwner().getLastName());

                claims.add(c);
            }

            data.put("claims", claims);
            data.put("status", "ok");
        } catch (Exception e) {
            data.put("status", "error");
        }

        json.add(data);
        return json.toJSONString();
    }

    public String getClaim(int id) {
        JSONArray json = new JSONArray();
        Map data = new HashMap();

        try {
            Criteria cr = ses.createCriteria(Db.InsuranceClaim.class);
            cr.add(Restrictions.eq("insuranceClaimId", id));
            Db.InsuranceClaim claim = (Db.InsuranceClaim) cr.uniqueResult();

            Map cData = new HashMap();

            cData.put("id", claim.getInsuranceClaimId());
            cData.put("damage", claim.getDamageType().getDamageType());
            cData.put("description", claim.getClaimDescription());
            cData.put("status", claim.getClaimStatus().getClaimStatus());
            InsuredVehical vehical = claim.getInsuredVehical();
            Map vData = new HashMap();

            vData.put("id", vehical.getInsuredVehicalId());
            vData.put("engin_no", vehical.getEnginNo());
            vData.put("chasis_no", vehical.getChasisNo());
            vData.put("color", vehical.getVehicalColor().getVehicalColor());
            vData.put("brand", vehical.getVehicalModel().getVehicalBrand().getVehicalBrand());
            vData.put("model", vehical.getVehicalModel().getVehicalModel());
            vData.put("status", vehical.getVehicalStatus().getVehicalStatus());
            vData.put("type", vehical.getVehicalType().getVehicalType());
            vData.put("year", vehical.getYearOfMade());
            VehicalOwner owner = vehical.getVehicalOwner();

            Map oData = new HashMap();

            //Owner
            oData.put("nic", owner.getVehicalOwnerNic());
            oData.put("fname", owner.getFirstName());
            oData.put("lname", owner.getLastName());
            oData.put("mobile", owner.getPrimaryMobile());

            vData.put("owner", oData);

            //coverage
            InsuranceCoverage coverage = vehical.getInsuranceCoverage();
            Map covData = new HashMap();

            covData.put("id", coverage.getInsuranceCoverageId());
            covData.put("amount", coverage.getCoverageAmount());
            covData.put("expire_date", dateFormat.format(coverage.getExpireDate()));
            covData.put("insured_date", dateFormat.format(coverage.getInsuredDate()));
            covData.put("type", coverage.getInsuranceType().getInsuranceType());
            covData.put("period_type", coverage.getInsuredPeriod().getInsuredPeriodType().getInsuredPeriodType());
            covData.put("period", coverage.getInsuredPeriod().getInsuredPeriod());

            vData.put("coverage", covData);

            cData.put("vehicle", vData);
            cData.put("date_time", dateTimeFormat.format(claim.getClaimDatetime()));

            data.put("claim", cData);
            data.put("status", "found");
        } catch (Exception e) {
            e.printStackTrace();
            data.put("status", "error");
        }

        json.add(data);
        return json.toJSONString();
    }

    public static void main(String[] args) {
        System.out.println(new Claim().getClaim(1));
    }

    public int assignAgent(int claim, String agent) {
        tr = ses.beginTransaction();
        try {
            Db.InsuranceClaim c = (Db.InsuranceClaim) ses.load(Db.InsuranceClaim.class, claim);

            c.setInsuranceAgent((InsuranceAgent) ses.load(Db.InsuranceAgent.class, agent));

            ses.update(c);

            tr.commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
