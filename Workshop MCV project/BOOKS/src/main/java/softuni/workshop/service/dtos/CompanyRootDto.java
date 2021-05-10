package softuni.workshop.service.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyRootDto {

    @XmlElement(name = "company")
    private List<CompnayDto> companyDtoList;

    public CompanyRootDto() {
    }

    public List<CompnayDto> getCompanyDtoList() {
        return companyDtoList;
    }

    public void setCompanyDtoList(List<CompnayDto> companyDtoList) {
        this.companyDtoList = companyDtoList;
    }
}
