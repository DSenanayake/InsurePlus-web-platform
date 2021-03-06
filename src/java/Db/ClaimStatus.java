package Db;
// Generated Mar 15, 2017 2:10:45 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * ClaimStatus generated by hbm2java
 */
public class ClaimStatus  implements java.io.Serializable {


     private Integer claimStatusId;
     private String claimStatus;
     private Set insuranceClaims = new HashSet(0);

    public ClaimStatus() {
    }

    public ClaimStatus(String claimStatus, Set insuranceClaims) {
       this.claimStatus = claimStatus;
       this.insuranceClaims = insuranceClaims;
    }
   
    public Integer getClaimStatusId() {
        return this.claimStatusId;
    }
    
    public void setClaimStatusId(Integer claimStatusId) {
        this.claimStatusId = claimStatusId;
    }
    public String getClaimStatus() {
        return this.claimStatus;
    }
    
    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }
    public Set getInsuranceClaims() {
        return this.insuranceClaims;
    }
    
    public void setInsuranceClaims(Set insuranceClaims) {
        this.insuranceClaims = insuranceClaims;
    }




}


