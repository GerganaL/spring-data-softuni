package softuni.exam.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture  extends BaseModel{
    @Column(unique = true)
    private String name;
    @Column(name = "date_and_time")
    private LocalDateTime dateAndTime;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToMany(mappedBy = "pictures")
    private Set<Offer> offers;

    public Picture() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
}
