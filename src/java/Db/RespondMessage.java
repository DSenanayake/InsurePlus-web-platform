package Db;
// Generated Mar 15, 2017 2:10:45 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * RespondMessage generated by hbm2java
 */
public class RespondMessage  implements java.io.Serializable {


     private Integer respondMessageId;
     private InsuranceClaim insuranceClaim;
     private String message;
     private Date dateTime;
     private Set replyForResponds = new HashSet(0);

    public RespondMessage() {
    }

	
    public RespondMessage(InsuranceClaim insuranceClaim) {
        this.insuranceClaim = insuranceClaim;
    }
    public RespondMessage(InsuranceClaim insuranceClaim, String message, Date dateTime, Set replyForResponds) {
       this.insuranceClaim = insuranceClaim;
       this.message = message;
       this.dateTime = dateTime;
       this.replyForResponds = replyForResponds;
    }
   
    public Integer getRespondMessageId() {
        return this.respondMessageId;
    }
    
    public void setRespondMessageId(Integer respondMessageId) {
        this.respondMessageId = respondMessageId;
    }
    public InsuranceClaim getInsuranceClaim() {
        return this.insuranceClaim;
    }
    
    public void setInsuranceClaim(InsuranceClaim insuranceClaim) {
        this.insuranceClaim = insuranceClaim;
    }
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getDateTime() {
        return this.dateTime;
    }
    
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    public Set getReplyForResponds() {
        return this.replyForResponds;
    }
    
    public void setReplyForResponds(Set replyForResponds) {
        this.replyForResponds = replyForResponds;
    }




}


