package com.example.demo.cardealer.domain.dto.export.lastExport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleExportRootDto {
    @XmlElement(name = "sale")
    private List<SaleExportDto> sales;

    public SaleExportRootDto() {
    }

    public List<SaleExportDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleExportDto> sales) {
        this.sales = sales;
    }
}
