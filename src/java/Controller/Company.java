package Controller;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class Company {

    private static SessionFactory sf;
    private final Session ses;
    private Transaction tr;
    public static final int OK = -10;
    public static final int FAILED = -5;

    public Company() {
        if (sf == null) {
            sf = Controller.Connection.getSessionFactory();
        }
        ses = sf.openSession();
    }

    public int login(String username, String password) throws Exception {
        Criteria cr = ses.createCriteria(Db.InsuranceCompany.class);
        cr.add(Restrictions.eq("username", username));
        cr.add(Restrictions.eq("password", password));
        Db.InsuranceCompany company = (Db.InsuranceCompany) cr.uniqueResult();
        if (company != null) {
            if (company.getUsername().equals(username) & company.getPassword().equals(password)) {
                System.out.println("[LOGIN] - Successful:" + company.getCompanyName());
                return company.getInsuranceCompanyId();
            }
        }
        System.out.println("[LOGIN] - Failed!:");
        return FAILED;
    }

    public int login(int id) throws Exception {
        Criteria cr = ses.createCriteria(Db.InsuranceCompany.class);
        cr.add(Restrictions.eq("insuranceCompanyId", id));
        Db.InsuranceCompany company = (Db.InsuranceCompany) cr.uniqueResult();
        if (company != null) {
            System.out.println("[LOGIN] - FOUND ID:" + company.getCompanyName());
            return company.getInsuranceCompanyId();
        }
        System.out.println("[LOGIN] - NOT FOUND!:");
        return FAILED;
    }

}
