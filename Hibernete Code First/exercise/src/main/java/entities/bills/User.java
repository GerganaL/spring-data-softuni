//package entities.bills;
//
//import entities.BaseEntity;
//
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//@Entity(name ="users")
//public class User extends BaseEntity {
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String password;
//    private BillingDetails billingDetails;
//
//    public User() {
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "billing_id", referencedColumnName = "id")
//    public BillingDetails getBillingDetails() {
//        return billingDetails;
//    }
//
//    public void setBillingDetails(BillingDetails billingDetails) {
//        this.billingDetails = billingDetails;
//    }
//}
