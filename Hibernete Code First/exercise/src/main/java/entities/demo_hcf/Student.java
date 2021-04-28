//package entities.demo_hcf;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "students")
//public class Student extends Person {
//    @Column(name = "grade")
//    private double grade;
//    @ManyToMany(mappedBy = "students", targetEntity = Course.class)
//    private Set<Course> courses;
//
//
//    public Set<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }
//
//
//    public Student() {
//    }
//
//    public Student(double grade) {
//        this.grade = grade;
//    }
//
//    public double getGrade() {
//        return grade;
//    }
//
//    public void setGrade(double grade) {
//        this.grade = grade;
//    }
//
//}
