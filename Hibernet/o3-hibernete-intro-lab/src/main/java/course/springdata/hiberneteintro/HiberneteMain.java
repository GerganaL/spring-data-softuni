package course.springdata.hiberneteintro;


import course.springdata.hiberneteintro.entity.Student;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class HiberneteMain {
    public static void main(String[] args) {
        //Create Hibernate config
        Configuration cfg = new Configuration();
        cfg.configure();

        // Create SessionFactory
        SessionFactory sf = cfg.buildSessionFactory();

        // Create Session
        Session session = sf.openSession();

        // Persist an entity
        Student student = new Student("Hristo Georgiev");
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();

        //Read entity by ID
        session.beginTransaction();
        session.setHibernateFlushMode(FlushMode.MANUAL);
      //  Student result = session.get(Student.class, 1L, LockMode.READ);
       // Student result = session.byId(Student.class).load(1L);
        long queryID = 1L;
        Optional<Student> result = session.byId(Student.class).loadOptional(queryID);
        session.getTransaction().commit();
        if(result.isPresent()){
            System.out.printf("Student: %s", result.get());
        }else{
            System.out.printf("Student with ID: %d does not exist%n",queryID);
        }

        //List all students using HQL
        session.beginTransaction();
        session.createQuery("FROM Student ", Student.class)
                .setFirstResult(5)
                .setMaxResults(10)
                .stream().forEach(System.out::println);
        session.getTransaction().commit();

        System.out.println("\n----------------------");
        //names
        session.createQuery("FROM Student WHERE name = :name", Student.class)
                .setParameter("name","Hristo Georgiev")
                .stream().forEach(System.out::println);
        //position
//        session.createQuery("FROM Student WHERE name = ?1", Student.class)
//                .setParameter(1,"Hristo Georgiev")
//                .stream().forEach(System.out::println);

        System.out.println("\n----------------------");
        //Criteria model api
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> Student_ = query.from(Student.class);
        CriteriaQuery<Student> name = query.select(Student_).where(cb.like(Student_.get("name"), "D%"));
        session.createQuery(name).getResultStream().forEach(System.out::println);

        //Close session
        session.close();
    }

}
