package course.springdata.cardealer.domain.dto.importDtos;

import com.google.gson.annotations.Expose;

public class CarSeedDto {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    //@SerializedName("travelled-distance")
    private long travelledDistance;

    public CarSeedDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
