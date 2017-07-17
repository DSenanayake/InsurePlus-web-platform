package Controller;

import Db.InsuredVehical;
import Db.OwnerStatus;
import Db.VehicalOwner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;

public class Owner {

    static SessionFactory sf;
    public static int ONLINE = 1;
    public static int OFFLINE = 2;
    public static int NOT_CONFIRMED = 3;
    public static int DISABLED = 4;
    Session ses;
    Transaction tr;
    private final SimpleDateFormat dateFormat;

    public Owner() {
        if (sf == null) {
            sf = Controller.Connection.getSessionFactory();
        }
        ses = sf.openSession();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public boolean isAvailable(String nic) {
        Criteria cr = ses.createCriteria(Db.VehicalOwner.class);
        cr.add(Restrictions.eq("vehicalOwnerNic", nic));

        return cr.uniqueResult() != null;
    }

    public void registerOwner(String nic, String fname, String lname, String dob, String address, String city, String mobile, String email, String profile, String password, int status) throws Exception {
        tr = ses.beginTransaction();

        VehicalOwner owner = new VehicalOwner();

        owner.setVehicalOwnerNic(nic);
        owner.setFirstName(fname);
        owner.setLastName(lname);
        owner.setDob(dateFormat.parse(dob));
        owner.setAddress(address);
        owner.setCity(new Controller.City().getCityByName(city));
        owner.setPrimaryMobile(mobile);
        owner.setProfilePic(profile);
        owner.setPassword(password);
        owner.setEmail(email);
        owner.setOwnerStatus((OwnerStatus) ses.load(Db.OwnerStatus.class, status));

        ses.saveOrUpdate(owner);

        tr.commit();
    }

    public String searchOwners(String keyword) {
        JSONArray array = new JSONArray();

        try {

            Criteria cr = ses.createCriteria(Db.VehicalOwner.class);
            if (keyword != null) {
                cr.add(Restrictions.or(Restrictions.like("vehicalOwnerNic", keyword, MatchMode.START), Restrictions.or(Restrictions.like("firstName", keyword, MatchMode.START), Restrictions.like("lastName", keyword, MatchMode.START))));
            }

            for (Object object : cr.list()) {
                Db.VehicalOwner owner = (Db.VehicalOwner) object;

                Map map = new HashMap();

                map.put("nic", owner.getVehicalOwnerNic());
                map.put("fname", owner.getFirstName());
                map.put("lname", owner.getLastName());
                map.put("profile", owner.getProfilePic());
                map.put("status", owner.getOwnerStatus().getOwnerStatus());

                array.add(map);
            }
        } catch (Exception e) {
        }

        return array.toJSONString();
    }

    public String getOwnerByNIC(String nic) {
        JSONArray json = new JSONArray();
        Map map = new HashMap();

        try {
            Db.VehicalOwner owner = (Db.VehicalOwner) ses.load(Db.VehicalOwner.class, nic);
            if (owner != null) {
                Map o = new HashMap();

                o.put("nic", owner.getVehicalOwnerNic());
                o.put("fname", owner.getFirstName());
                o.put("lname", owner.getLastName());
                o.put("email", owner.getEmail());
                o.put("dob", dateFormat.format(owner.getDob()));
                o.put("address", owner.getAddress());
                o.put("city", owner.getCity().getCity());
                o.put("profile", owner.getProfilePic());
                o.put("mobile", owner.getPrimaryMobile());
                o.put("status", owner.getOwnerStatus().getOwnerStatus());

                map.put("owner", o);
                map.put("status", "found");
            } else {
                map.put("status", "na");
            }
        } catch (Exception e) {
            map.put("status", "na");
        }

        json.add(map);
        return json.toJSONString();
    }

    public static void main(String[] args) {
        try {
//            System.out.println(new Owner().loginOwner("951733083v", "password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loginOwner(String nic, String password) throws Exception {
        JSONArray array = new JSONArray();
        Map m = new HashMap<String, String>();

        m.put("status", "na");
        try {
            Criteria cr = ses.createCriteria(VehicalOwner.class);
            cr.add(Restrictions.eq("vehicalOwnerNic", nic));
            cr.add(Restrictions.eq("password", password));

            Db.VehicalOwner owner = (Db.VehicalOwner) cr.uniqueResult();
            if (owner != null) {
                if (owner.getPassword().equals(password)) {
                    Map o = new HashMap<>();

                    o.put("nic", owner.getVehicalOwnerNic());
                    o.put("fname", owner.getFirstName());
                    o.put("lname", owner.getLastName());
                    o.put("password", owner.getPassword());
                    o.put("email", owner.getEmail());
                    o.put("dob", dateFormat.format(owner.getDob()));
                    o.put("address", owner.getAddress());
                    o.put("city", owner.getCity().getCity());
                    o.put("mobile", owner.getPrimaryMobile());
                    o.put("status", owner.getOwnerStatus().getOwnerStatus());

                    m.put("owner", o);

                    m.put("status", "found");
                }
            }
        } catch (Exception e) {
            throw e;
        }

        array.add(m);
        return array.toJSONString();
    }

    String getVehicles(String nic) {
        JSONArray json = new JSONArray();
        Map data = new HashMap();

        try {
            List vehicles = new ArrayList();
            Criteria cr = ses.createCriteria(Db.InsuredVehical.class);
            cr.add(Restrictions.eq("vehicalOwner", (Db.VehicalOwner) ses.load(Db.VehicalOwner.class, nic)));
            List<Db.InsuredVehical> list = cr.list();
            for (InsuredVehical vehicle : list) {
                Map v = new HashMap<>();

                v.put("id", vehicle.getInsuredVehicalId());
                v.put("model", vehicle.getVehicalModel().getVehicalModel());
                v.put("brand", vehicle.getVehicalModel().getVehicalBrand().getVehicalBrand());
                v.put("eng_no", vehicle.getEnginNo());
                v.put("chasis_no", vehicle.getChasisNo());
                v.put("year", vehicle.getYearOfMade());
                v.put("type", vehicle.getVehicalType().getVehicalType());

                vehicles.add(v);
            }
            data.put("vehicles", vehicles);
            data.put("status", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            data.put("status", "error");
        }

        json.add(data);
        return json.toJSONString();
    }

}
