package softuni.exam.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerRootImportDto {

    @XmlElement(name = "seller")
    private List<SellerImportDto> sellerImportDtos;

    public SellerRootImportDto() {
    }

    public List<SellerImportDto> getSellerImportDtos() {
        return sellerImportDtos;
    }

    public void setSellerImportDtos(List<SellerImportDto> sellerImportDtos) {
        this.sellerImportDtos = sellerImportDtos;
    }
}
