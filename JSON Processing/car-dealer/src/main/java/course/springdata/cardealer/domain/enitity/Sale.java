package course.springdata.cardealer.domain.enitity;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity{
    private int discount;
    private Car car;
    private Customer customer;

    public Sale() {
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @OneToOne
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
