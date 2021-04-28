package enitity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "plate")
@Table(name = "plates")
public class PlateNumber {
    @Id
    private Long id; // this id is mapping to car id - both are with same id
    @Column(name = "plate_number")
    private String plateNumber;
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Car car;

    public PlateNumber() {
    }

    public PlateNumber(String plateNumber, Car car) {
        this.plateNumber = plateNumber;
        this.car = car;
    }

    public PlateNumber(Long id, String plateNumber, Car car) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlateNumber that = (PlateNumber) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PlateNumber{" +
                "id=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                '}';
    }
}
