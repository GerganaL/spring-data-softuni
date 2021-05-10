package com.example.demo.cardealer.domain.dto.export;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportRootDto {

    @XmlElement(name = "car")
    private List<CarExportDto> carExportDtos;

    public CarExportRootDto() {
    }

    public List<CarExportDto> getCarExportDtos() {
        return carExportDtos;
    }

    public void setCarExportDtos(List<CarExportDto> carExportDtos) {
        this.carExportDtos = carExportDtos;
    }
}