package Db;
// Generated Mar 15, 2017 2:10:45 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * OwnerStatus generated by hbm2java
 */
public class OwnerStatus  implements java.io.Serializable {


     private Integer ownerStatusId;
     private String ownerStatus;
     private Set vehicalOwners = new HashSet(0);

    public OwnerStatus() {
    }

    public OwnerStatus(String ownerStatus, Set vehicalOwners) {
       this.ownerStatus = ownerStatus;
       this.vehicalOwners = vehicalOwners;
    }
   
    public Integer getOwnerStatusId() {
        return this.ownerStatusId;
    }
    
    public void setOwnerStatusId(Integer ownerStatusId) {
        this.ownerStatusId = ownerStatusId;
    }
    public String getOwnerStatus() {
        return this.ownerStatus;
    }
    
    public void setOwnerStatus(String ownerStatus) {
        this.ownerStatus = ownerStatus;
    }
    public Set getVehicalOwners() {
        return this.vehicalOwners;
    }
    
    public void setVehicalOwners(Set vehicalOwners) {
        this.vehicalOwners = vehicalOwners;
    }




}


