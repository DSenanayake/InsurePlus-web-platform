package Db;
// Generated Mar 15, 2017 2:10:45 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * InsuranceCoverage generated by hbm2java
 */
public class InsuranceCoverage  implements java.io.Serializable {


     private Integer insuranceCoverageId;
     private InsuranceType insuranceType;
     private InsuredPeriod insuredPeriod;
     private Double coverageAmount;
     private Date insuredDate;
     private Date expireDate;
     private Set insuredVehicals = new HashSet(0);

    public InsuranceCoverage() {
    }

	
    public InsuranceCoverage(InsuranceType insuranceType, InsuredPeriod insuredPeriod) {
        this.insuranceType = insuranceType;
        this.insuredPeriod = insuredPeriod;
    }
    public InsuranceCoverage(InsuranceType insuranceType, InsuredPeriod insuredPeriod, Double coverageAmount, Date insuredDate, Date expireDate, Set insuredVehicals) {
       this.insuranceType = insuranceType;
       this.insuredPeriod = insuredPeriod;
       this.coverageAmount = coverageAmount;
       this.insuredDate = insuredDate;
       this.expireDate = expireDate;
       this.insuredVehicals = insuredVehicals;
    }
   
    public Integer getInsuranceCoverageId() {
        return this.insuranceCoverageId;
    }
    
    public void setInsuranceCoverageId(Integer insuranceCoverageId) {
        this.insuranceCoverageId = insuranceCoverageId;
    }
    public InsuranceType getInsuranceType() {
        return this.insuranceType;
    }
    
    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }
    public InsuredPeriod getInsuredPeriod() {
        return this.insuredPeriod;
    }
    
    public void setInsuredPeriod(InsuredPeriod insuredPeriod) {
        this.insuredPeriod = insuredPeriod;
    }
    public Double getCoverageAmount() {
        return this.coverageAmount;
    }
    
    public void setCoverageAmount(Double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }
    public Date getInsuredDate() {
        return this.insuredDate;
    }
    
    public void setInsuredDate(Date insuredDate) {
        this.insuredDate = insuredDate;
    }
    public Date getExpireDate() {
        return this.expireDate;
    }
    
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    public Set getInsuredVehicals() {
        return this.insuredVehicals;
    }
    
    public void setInsuredVehicals(Set insuredVehicals) {
        this.insuredVehicals = insuredVehicals;
    }




}

