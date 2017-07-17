package Controller;

import Db.InsuranceCompany;
import Db.InsuranceType;
import Db.InsuredPeriodType;
import Db.InsuredVehical;
import Db.VehicalBrand;
import Db.VehicalColor;
import Db.VehicalModel;
import Db.VehicalOwner;
import Db.VehicalStatus;
import Db.VehicalType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;

public class Vehicle {

    private static SessionFactory sf;
    private final Session ses;
    private Transaction tr;

    public static final int OK = 65;
    public static final int ERROR = 89;
    private static final int STATUS_INSURED = 1;
    private static final int STATUS_EXPIRED = 2;
    private static final int STATUS_DISABLED = 3;
    private static final int STATUS_DAMAGE = 4;
    private final SimpleDateFormat dateFormat;

    public Vehicle() {
        if (sf == null) {
            sf = Connection.getSessionFactory();
        }
        this.ses = sf.openSession();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public int registerVehicle(String chasisNo, String enginNo, String vehicleNo, String color, String model, String brand, String owner, String type, Integer year, String amount, String expireDate, String insuredDate, int insuranceType, String insurancePeriod, int periodType) {
        Transaction t = ses.beginTransaction();
        try {

            Db.InsuredVehical vehicle = new Db.InsuredVehical();

            vehicle.setChasisNo(chasisNo);
            vehicle.setEnginNo(enginNo);
            vehicle.setInsuranceCompany((InsuranceCompany) ses.load(Db.InsuranceCompany.class, 1));
            vehicle.setInsuredVehicalId(vehicleNo);
            vehicle.setVehicalColor(getColor(color));
            vehicle.setVehicalModel(getModel(model, brand));
            vehicle.setVehicalOwner((VehicalOwner) ses.load(Db.VehicalOwner.class, owner));
            vehicle.setVehicalStatus((VehicalStatus) ses.load(Db.VehicalStatus.class, STATUS_INSURED));
            vehicle.setVehicalType(getVehicleType(type));
            vehicle.setYearOfMade(year);

            Db.InsuranceCoverage coverage = new Db.InsuranceCoverage();

            coverage.setCoverageAmount(Double.parseDouble(amount));
            coverage.setExpireDate(dateFormat.parse(expireDate));
            coverage.setInsuredDate(dateFormat.parse(insuredDate));
            coverage.setInsuranceType((InsuranceType) ses.load(Db.InsuranceType.class, insuranceType));

            //Set Insurance Period
            Db.InsuredPeriod period = new Db.InsuredPeriod();

            period.setInsuredPeriod(Double.parseDouble(insurancePeriod));
            period.setInsuredPeriodType((InsuredPeriodType) ses.load(Db.InsuredPeriodType.class, periodType));

            ses.save(period);

            coverage.setInsuredPeriod(period);

            ses.save(coverage);

            vehicle.setInsuranceCoverage(coverage);

            ses.save(vehicle);

            t.commit();
            return OK;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    private VehicalColor getColor(String color) {
        try {
            Criteria cr = ses.createCriteria(Db.VehicalColor.class);
            cr.add(Restrictions.eq("vehicalColor", color));
            Db.VehicalColor c = (Db.VehicalColor) cr.uniqueResult();
            if (c != null) {
                return c;
            } else {
//                Transaction t = ses.beginTransaction();
                Db.VehicalColor newColor = new Db.VehicalColor();

                newColor.setVehicalColor(color);
                ses.save(newColor);

//                t.commit();
                return newColor;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private VehicalModel getModel(String model, String brand) {
        try {
            Criteria cr = ses.createCriteria(Db.VehicalModel.class);
            cr.add(Restrictions.eq("vehicalModel", model));
            Db.VehicalModel c = (Db.VehicalModel) cr.uniqueResult();
            if (c != null) {
                return c;
            } else {
//                Transaction t = ses.beginTransaction();
                Db.VehicalModel newModel = new Db.VehicalModel();

                newModel.setVehicalModel(model);
                newModel.setVehicalBrand(getBrand(brand));

                ses.save(newModel);

//                t.commit();
                return newModel;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private VehicalBrand getBrand(String brand) {
        try {
            Criteria cr = ses.createCriteria(Db.VehicalBrand.class);
            cr.add(Restrictions.eq("vehicalBrand", brand));
            Db.VehicalBrand c = (Db.VehicalBrand) cr.uniqueResult();
            if (c != null) {
                return c;
            } else {
//                Transaction t = ses.beginTransaction();
                Db.VehicalBrand newBrand = new Db.VehicalBrand();

                newBrand.setVehicalBrand(brand);

                ses.save(newBrand);

//                t.commit();
                return newBrand;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private VehicalType getVehicleType(String type) {
        try {
            Criteria cr = ses.createCriteria(Db.VehicalType.class);
            cr.add(Restrictions.eq("vehicalType", type));
            Db.VehicalType c = (Db.VehicalType) cr.uniqueResult();
            if (c != null) {
                return c;
            } else {
//                Transaction t = ses.beginTransaction();
                Db.VehicalType newType = new Db.VehicalType();

                newType.setVehicalType(type);

                ses.save(newType);

//                t.commit();
                return newType;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Vehicle().registerVehicle("ABCEDFG", "AAAAAAA", "BAD", "Black", "KDH", "Toyota", "951733083v", "Van", 2015, "5000000", "2015-12-01", "2016-12-01", 1, "1", 1));
    }

    public String getVehicles() {
        JSONArray json = new JSONArray();
        Map data = new HashMap();
        try {
            Criteria cr = ses.createCriteria(Db.InsuredVehical.class);
            List<Db.InsuredVehical> list = cr.list();

            List v = new ArrayList<Object>();
            for (InsuredVehical vehical : list) {
                Map c = new HashMap();

                c.put("vehicleId", vehical.getInsuredVehicalId());
                c.put("chasisNo", vehical.getChasisNo());
                c.put("engineNo", vehical.getEnginNo());
                c.put("color", vehical.getVehicalColor().getVehicalColor());

                v.add(c);
            }

            data.put("status", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            data.put("status", "error");
        }
        json.add(data);
        return json.toJSONString();
    }
}
