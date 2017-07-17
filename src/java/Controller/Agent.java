package Controller;

import Db.AgentStatus;
import Db.InsuranceCompany;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;

public class Agent {

    private static SessionFactory sf;
    Session ses;
    Transaction tr;
    public static final int OK = 0;
    private final SimpleDateFormat dateFromat;

    public Agent() {
        if (sf == null) {
            sf = Connection.getSessionFactory();
        }
        ses = sf.openSession();
        dateFromat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public int registerAgent(String nic, int company, String fname, String lname, String dob, String profile_pic, int status) throws Exception {
        try {
            tr = ses.beginTransaction();

            Db.InsuranceAgent agent = new Db.InsuranceAgent();

            agent.setAgentNic(nic);
            agent.setInsuranceCompany((InsuranceCompany) ses.load(Db.InsuranceCompany.class, company));
            agent.setFirstName(fname);
            agent.setLastName(lname);
            agent.setDob(dateFromat.parse(dob));
            agent.setProfileImgUrl(profile_pic);
            agent.setAgentStatus((AgentStatus) ses.load(Db.AgentStatus.class, status));

            ses.saveOrUpdate(agent);

            tr.commit();
            return OK;
        } catch (HibernateException e) {
            throw e;
        }
    }

    public static void main(String[] args) throws Exception {
        new Agent().registerAgent("951733083V", 1, "Deeptha", "Senanayake", "1995-06-21", null, 1);
    }

    public String getAgents(String keyword) throws Exception {
        JSONArray json = new JSONArray();

        Criteria cr = ses.createCriteria(Db.InsuranceAgent.class);
        if (keyword != null) {
            cr.add(Restrictions.or(Restrictions.like("agentNic", keyword, MatchMode.START), Restrictions.or(Restrictions.like("firstName", keyword, MatchMode.START), Restrictions.like("lastName", keyword, MatchMode.START))));
        }

        for (Object object : cr.list()) {
            Db.InsuranceAgent agent = (Db.InsuranceAgent) object;

            Map map = new HashMap();

            map.put("nic", agent.getAgentNic());
            map.put("fname", agent.getFirstName());
            map.put("lname", agent.getLastName());
            map.put("profile", agent.getProfileImgUrl());
            map.put("status", agent.getAgentStatus().getAgentStatus());

            json.add(map);
        }

        return json.toJSONString();
    }

    public String getAgent(String nic) {
        String html = "NA";

        try {
            Db.InsuranceAgent agent = (Db.InsuranceAgent) ses.load(Db.InsuranceAgent.class, nic);
            if (agent != null) {
                html = agent.getFirstName() + " " + agent.getLastName();
            }
        } catch (Exception e) {
        }
        return html;
    }

}
