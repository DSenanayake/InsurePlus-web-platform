package Db;
// Generated Mar 15, 2017 2:10:45 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * EvidencePhotoType generated by hbm2java
 */
public class EvidencePhotoType  implements java.io.Serializable {


     private Integer evidencePhotoTypeId;
     private String evidencePhotoType;
     private Set evidencePhotos = new HashSet(0);

    public EvidencePhotoType() {
    }

    public EvidencePhotoType(String evidencePhotoType, Set evidencePhotos) {
       this.evidencePhotoType = evidencePhotoType;
       this.evidencePhotos = evidencePhotos;
    }
   
    public Integer getEvidencePhotoTypeId() {
        return this.evidencePhotoTypeId;
    }
    
    public void setEvidencePhotoTypeId(Integer evidencePhotoTypeId) {
        this.evidencePhotoTypeId = evidencePhotoTypeId;
    }
    public String getEvidencePhotoType() {
        return this.evidencePhotoType;
    }
    
    public void setEvidencePhotoType(String evidencePhotoType) {
        this.evidencePhotoType = evidencePhotoType;
    }
    public Set getEvidencePhotos() {
        return this.evidencePhotos;
    }
    
    public void setEvidencePhotos(Set evidencePhotos) {
        this.evidencePhotos = evidencePhotos;
    }




}

