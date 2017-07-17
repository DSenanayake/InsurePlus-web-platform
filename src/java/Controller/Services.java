package Controller;

import com.sun.mail.smtp.SMTPTransport;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Services {

    public static void sendEmail(String email, String subject, String message, String pat) throws javax.mail.MessagingException {

        String[] guy = {email};
        String fnm = pat;                              // attatching file path............
        String from = "pickme.lk@gmail.com";
        String pass = "pickme.lk2015";
        String host = "smtp.gmail.com";

        Properties prop = new Properties();
        prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.socketFactory.port", 465);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.user", from);
        prop.put("mail.smtp.password", pass);
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");

        Session ses = Session.getDefaultInstance(prop, null);

        javax.mail.internet.MimeMessage mm = new MimeMessage(ses);

        mm.setFrom(new InternetAddress(from));

        InternetAddress[] ias = new InternetAddress[guy.length];

        for (int i = 0; i < guy.length; i++) {
            ias[i] = new InternetAddress(guy[i]);
        }

        for (int i = 0; i < ias.length; i++) {
            mm.addRecipients(Message.RecipientType.TO, ias);
        }
        mm.setSubject(subject);
//            mm.setContent("<img src=" + "<h1>" + Message + "</h1>", "text/html");
        mm.setContent(message, "text/html");

        //MimeBodyPart mbp=new MimeBodyPart();
        //mbp.attachFile(fnm);
        //Multipart mp=new MimeMultipart();
        //mp.addBodyPart(mbp);
        //mm.setContent(mp);
        SMTPTransport tran = (SMTPTransport) ses.getTransport("smtp");

        System.out.print("[SERVICES - EMAIL SERVICE] - Attempting to Login.");
        tran.connect(host, 465, from, pass);
        System.out.println(".");
        tran.sendMessage(mm, mm.getAllRecipients());

        tran.close();

        System.out.println("[SERVICES - EMAIL SERVICE] - Sent.");

    }
}
