package enitity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicle {
    @Column(name = "load_capacity")
    private double loadCapacity;

    public Truck() {
    }

    public Truck(String type, BigDecimal price, String fuelType, double loadCapacity) {
        super(type, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

    public Truck(Long id, String type, BigDecimal price, String fuelType, double loadCapacity) {
        super(id, type, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Truck{");
        sb.append(super.toString());
        sb.append("load capacity=").append(loadCapacity);
        sb.append('}');
        return sb.toString();
    }
}
