//package entities.demo_hcf;
//
//import entities.BaseEntity;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "courses")
//public class Course extends BaseEntity {
//
//    @Column(name = "name", nullable = false, unique = true)
//    private String name;
//
//    @ManyToMany
//    @JoinTable(name = "courses_students",
//            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "students_id",referencedColumnName = "id"))
//    private Set<Student> students;
//    @ManyToOne
//    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
//    private Teacher teacher;
//
//
//
//    public Teacher getTeacher() {
//        return teacher;
//    }
//
//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//    }
//
//
//    public Set<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(Set<Student> students) {
//        this.students = students;
//    }
//
//    public Course(){
//
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
