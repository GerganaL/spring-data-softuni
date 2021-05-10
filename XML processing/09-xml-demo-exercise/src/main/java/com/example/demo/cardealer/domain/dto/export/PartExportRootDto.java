package com.example.demo.cardealer.domain.dto.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartExportRootDto {

    @XmlElement(name = "part")
    private List<PartExportDto> parts;

    public PartExportRootDto() {
    }

    public List<PartExportDto> getParts() {
        return parts;
    }

    public void setParts(List<PartExportDto> parts) {
        this.parts = parts;
    }
}
