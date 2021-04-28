//package entities.hospital;
//import entities.BaseEntity;
//import javax.persistence.*;
//import java.util.Date;
//import java.util.Set;
//
//
//@Entity(name = "patients")
//public class Patient extends BaseEntity {
//
//    @Column(nullable = false, length = 30)
//    private String firstName;
//
//    @Column(nullable = false, length = 30)
//    private String lastName;
//
//    @Column(nullable = false, length = 120)
//    private String address;
//
//    @Column(nullable = false, unique = true, length = 30)
//    private String email;
//
//    @Basic
//    private Date birthDate;
//
//    @Lob
//    private byte[] picture;
//
//    @Column(name = "medical_insurance")
//    private boolean hasMedicalInsurance;
//    private Set<Visitation> visitations;
//    private Set<Diagnose> diagnoses;
//
//
//    private Set<Medicament> prescriptions;
//
//    public String getFirstName() {
//        return this.firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return this.lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getAddress() {
//        return this.address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getEmail() {
//        return this.email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Date getBirthDate() {
//        return this.birthDate;
//    }
//
//    public void setBirthDate(Date birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    public byte[] getPicture() {
//        return this.picture;
//    }
//
//    public void setPicture(byte[] picture) {
//        this.picture = picture;
//    }
//
//    public boolean isHasMedicalInsurance() {
//        return this.hasMedicalInsurance;
//    }
//
//    public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
//        this.hasMedicalInsurance = hasMedicalInsurance;
//    }
//
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    public Set<Visitation> getVisitations() {
//        return this.visitations;
//    }
//
//    public void setVisitations(Set<Visitation> visitations) {
//        this.visitations = visitations;
//    }
//
//    @ManyToMany
//    @JoinTable(name = "patients_diagnoses",
//            joinColumns = @JoinColumn(name = "patient_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "diagnose_id",referencedColumnName = "id"))
//    public Set<Diagnose> getDiagnoses() {
//        return this.diagnoses;
//    }
//
//    public void setDiagnoses(Set<Diagnose> diagnoses) {
//        this.diagnoses = diagnoses;
//    }
//
//    @ManyToMany
//    @JoinTable(name = "patients_prescriptions",
//            joinColumns = @JoinColumn(name = "patient_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "prescription_id",referencedColumnName = "id"))
//    public Set<Medicament> getPrescriptions() {
//        return this.prescriptions;
//    }
//
//    public void setPrescriptions(Set<Medicament> prescriptions) {
//        this.prescriptions = prescriptions;
//    }
//}
