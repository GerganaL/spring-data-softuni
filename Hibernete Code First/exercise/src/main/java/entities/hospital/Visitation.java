//package entities.hospital;
//
//import entities.BaseEntity;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.Date;
//
//@Entity(name = "visitations")
//public class Visitation extends BaseEntity {
//
//    @Column(nullable = false)
//    private Date date;
//
//    @Column(length = 100)
//    private String comments;
//
//    private Patient patient;
//
//
//    @ManyToOne
//    @JoinColumn(name = "patient_id",referencedColumnName = "id")
//    public Patient getPatient() {
//        return this.patient;
//    }
//
//    public void setPatient(Patient patient) {
//        this.patient = patient;
//    }
//
//    public Date getDate() {
//        return this.date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
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
