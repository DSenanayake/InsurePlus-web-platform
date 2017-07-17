package Controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class City {

    private static SessionFactory sf;
    Session ses;
    Transaction tr;

    public City() {
        if (sf == null) {
            sf = Connection.getSessionFactory();
        }
        ses = sf.openSession();
    }

    Db.City getCityByName(String city) throws Exception {
        try {
            Criteria cr = ses.createCriteria(Db.City.class);
            cr.add(Restrictions.eq("city", city));
            Db.City c = (Db.City) cr.uniqueResult();
            if (c != null) {
                return c;
            } else {
                tr = ses.beginTransaction();
                Db.City newC = new Db.City();

                newC.setCity(city);
                ses.save(newC);

                tr.commit();
                return newC;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            new City().getCityByName("Bowatta");
        } catch (Exception ex) {
            Logger.getLogger(City.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
