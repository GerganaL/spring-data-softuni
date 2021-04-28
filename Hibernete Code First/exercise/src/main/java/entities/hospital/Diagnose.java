//package entities.hospital;
//
//import entities.BaseEntity;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity(name = "diagnoses")
//public class Diagnose extends BaseEntity {
//
//    @Column(nullable = false, length = 30)
//    private String name;
//
//    @Column(length = 100)
//    private String comments;
//
//
//    private Set<Patient> patients;
//
//    @ManyToMany(mappedBy = "diagnoses", targetEntity = Patient.class)
//    public Set<Patient> getPatients() {
//        return this.patients;
//    }
//
//    public void setPatients(Set<Patient> patients) {
//        this.patients = patients;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getComments() {
//        return this.comments;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//}
