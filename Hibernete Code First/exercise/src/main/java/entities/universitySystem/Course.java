package entities.universitySystem;

import entities.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    private String name;
    private String description;
    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endDate;
    private int credits;
    private Set<Student> students;
    private Set<Teacher> teachers;

    public Course() {
    }

    @OneToMany(mappedBy = "course", targetEntity = Teacher.class)
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }


    @ManyToMany(mappedBy = "courses")
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "start_date")
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
