package course.springdata.jpaintro.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

//@Entity
@Table(name = "students")
public class StudentOld {
    private Long id;
    private String name;
    private Date registrationDate = new Date();

    public StudentOld(){

    }

    public StudentOld(String name) {
        this.name = name;
    }

    public StudentOld(Long id, String name, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    @Column(name = "registration_date")
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
