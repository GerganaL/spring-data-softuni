package course.springdata.xmldemo.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Address {
    @XmlAttribute(required = true)
    private Long id;
    @NonNull
    @XmlElement(required = true)
    private String country;
    @XmlElement(required = true)
    @NonNull
    private String city;
    @NonNull
    private String street;
}
