package Db;
// Generated Mar 15, 2017 2:10:45 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * ReplyForRespond generated by hbm2java
 */
public class ReplyForRespond  implements java.io.Serializable {


     private Integer replyForRespondId;
     private RespondMessage respondMessage;
     private String reply;
     private Date dateTime;

    public ReplyForRespond() {
    }

	
    public ReplyForRespond(RespondMessage respondMessage) {
        this.respondMessage = respondMessage;
    }
    public ReplyForRespond(RespondMessage respondMessage, String reply, Date dateTime) {
       this.respondMessage = respondMessage;
       this.reply = reply;
       this.dateTime = dateTime;
    }
   
    public Integer getReplyForRespondId() {
        return this.replyForRespondId;
    }
    
    public void setReplyForRespondId(Integer replyForRespondId) {
        this.replyForRespondId = replyForRespondId;
    }
    public RespondMessage getRespondMessage() {
        return this.respondMessage;
    }
    
    public void setRespondMessage(RespondMessage respondMessage) {
        this.respondMessage = respondMessage;
    }
    public String getReply() {
        return this.reply;
    }
    
    public void setReply(String reply) {
        this.reply = reply;
    }
    public Date getDateTime() {
        return this.dateTime;
    }
    
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }




}


