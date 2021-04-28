package enitity;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "cars")
public class Car extends Vehicle{
    private int seats;
    @OneToOne (mappedBy = "car")
    private PlateNumber plate;

    public PlateNumber getPlate() {
        return plate;
    }

    public void setPlate(PlateNumber plate) {
        this.plate = plate;
    }

    public Car() {
    }

    public Car(String type, BigDecimal price, String fuelType, int seats) {
        super(type, price, fuelType);
        this.seats = seats;
    }

    public Car(Long id, String type, BigDecimal price, String fuelType, int seats) {
        super(id, type, price, fuelType);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append(super.toString());
        sb.append("seats=").append(seats);
        sb.append(", plate= ").append(plate);
        sb.append('}');
        return sb.toString();
    }


}
