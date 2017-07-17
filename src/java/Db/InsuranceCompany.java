package Db;
// Generated Mar 15, 2017 2:10:45 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * InsuranceCompany generated by hbm2java
 */
public class InsuranceCompany  implements java.io.Serializable {


     private Integer insuranceCompanyId;
     private City city;
     private CompanyLogo companyLogo;
     private CompanyStatus companyStatus;
     private String companyName;
     private String companyDescription;
     private String hotLine;
     private String companyAddress;
     private String username;
     private String password;
     private Date registeredDate;
     private Set insuredVehicals = new HashSet(0);
     private Set insuranceAgents = new HashSet(0);

    public InsuranceCompany() {
    }

	
    public InsuranceCompany(City city, CompanyLogo companyLogo, CompanyStatus companyStatus) {
        this.city = city;
        this.companyLogo = companyLogo;
        this.companyStatus = companyStatus;
    }
    public InsuranceCompany(City city, CompanyLogo companyLogo, CompanyStatus companyStatus, String companyName, String companyDescription, String hotLine, String companyAddress, String username, String password, Date registeredDate, Set insuredVehicals, Set insuranceAgents) {
       this.city = city;
       this.companyLogo = companyLogo;
       this.companyStatus = companyStatus;
       this.companyName = companyName;
       this.companyDescription = companyDescription;
       this.hotLine = hotLine;
       this.companyAddress = companyAddress;
       this.username = username;
       this.password = password;
       this.registeredDate = registeredDate;
       this.insuredVehicals = insuredVehicals;
       this.insuranceAgents = insuranceAgents;
    }
   
    public Integer getInsuranceCompanyId() {
        return this.insuranceCompanyId;
    }
    
    public void setInsuranceCompanyId(Integer insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }
    public City getCity() {
        return this.city;
    }
    
    public void setCity(City city) {
        this.city = city;
    }
    public CompanyLogo getCompanyLogo() {
        return this.companyLogo;
    }
    
    public void setCompanyLogo(CompanyLogo companyLogo) {
        this.companyLogo = companyLogo;
    }
    public CompanyStatus getCompanyStatus() {
        return this.companyStatus;
    }
    
    public void setCompanyStatus(CompanyStatus companyStatus) {
        this.companyStatus = companyStatus;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyDescription() {
        return this.companyDescription;
    }
    
    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }
    public String getHotLine() {
        return this.hotLine;
    }
    
    public void setHotLine(String hotLine) {
        this.hotLine = hotLine;
    }
    public String getCompanyAddress() {
        return this.companyAddress;
    }
    
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getRegisteredDate() {
        return this.registeredDate;
    }
    
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
    public Set getInsuredVehicals() {
        return this.insuredVehicals;
    }
    
    public void setInsuredVehicals(Set insuredVehicals) {
        this.insuredVehicals = insuredVehicals;
    }
    public Set getInsuranceAgents() {
        return this.insuranceAgents;
    }
    
    public void setInsuranceAgents(Set insuranceAgents) {
        this.insuranceAgents = insuranceAgents;
    }




}


