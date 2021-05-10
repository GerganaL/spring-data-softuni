package softuni.workshop.data.entities;

import jdk.jfr.Enabled;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity{
    //•	id – integer number, primary identification field.
    //•	first_name – a string (required).
    //•	last_name – a string (required).
    //•	age – a Integer(required).
    //•	project – a Project entity(required).
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private int age;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private  Project project;

    public Employee() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
