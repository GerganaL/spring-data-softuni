//package entities.bills;
//
//import entities.BaseEntity;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//public abstract class BillingDetails extends BaseEntity {
//    private int number;
//    private Set<User> users;
//
//    public BillingDetails() {
//    }
//
//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }
//
//  @OneToMany(mappedBy = "billingDetails",targetEntity = User.class)
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//}
