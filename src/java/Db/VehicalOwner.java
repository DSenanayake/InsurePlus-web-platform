package Db;
// Generated Mar 15, 2017 2:10:45 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * VehicalOwner generated by hbm2java
 */
public class VehicalOwner  implements java.io.Serializable {


     private String vehicalOwnerNic;
     private City city;
     private OwnerStatus ownerStatus;
     private String firstName;
     private String lastName;
     private String password;
     private String email;
     private Date dob;
     private String address;
     private String profilePic;
     private String primaryMobile;
     private Set insuredVehicals = new HashSet(0);

    public VehicalOwner() {
    }

	
    public VehicalOwner(String vehicalOwnerNic, City city, OwnerStatus ownerStatus) {
        this.vehicalOwnerNic = vehicalOwnerNic;
        this.city = city;
        this.ownerStatus = ownerStatus;
    }
    public VehicalOwner(String vehicalOwnerNic, City city, OwnerStatus ownerStatus, String firstName, String lastName, String password, String email, Date dob, String address, String profilePic, String primaryMobile, Set insuredVehicals) {
       this.vehicalOwnerNic = vehicalOwnerNic;
       this.city = city;
       this.ownerStatus = ownerStatus;
       this.firstName = firstName;
       this.lastName = lastName;
       this.password = password;
       this.email = email;
       this.dob = dob;
       this.address = address;
       this.profilePic = profilePic;
       this.primaryMobile = primaryMobile;
       this.insuredVehicals = insuredVehicals;
    }
   
    public String getVehicalOwnerNic() {
        return this.vehicalOwnerNic;
    }
    
    public void setVehicalOwnerNic(String vehicalOwnerNic) {
        this.vehicalOwnerNic = vehicalOwnerNic;
    }
    public City getCity() {
        return this.city;
    }
    
    public void setCity(City city) {
        this.city = city;
    }
    public OwnerStatus getOwnerStatus() {
        return this.ownerStatus;
    }
    
    public void setOwnerStatus(OwnerStatus ownerStatus) {
        this.ownerStatus = ownerStatus;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDob() {
        return this.dob;
    }
    
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getProfilePic() {
        return this.profilePic;
    }
    
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    public String getPrimaryMobile() {
        return this.primaryMobile;
    }
    
    public void setPrimaryMobile(String primaryMobile) {
        this.primaryMobile = primaryMobile;
    }
    public Set getInsuredVehicals() {
        return this.insuredVehicals;
    }
    
    public void setInsuredVehicals(Set insuredVehicals) {
        this.insuredVehicals = insuredVehicals;
    }




}


