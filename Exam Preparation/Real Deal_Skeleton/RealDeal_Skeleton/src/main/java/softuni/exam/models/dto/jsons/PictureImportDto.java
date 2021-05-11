package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import softuni.exam.models.Car;

import java.time.LocalDateTime;

public class PictureImportDto {
    @Expose
    @Length(min = 2, max = 20)
    private String name;
    @Expose
    private LocalDateTime dateAndTime;
    @Expose
    private int car;

    public PictureImportDto() {
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

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }
}
