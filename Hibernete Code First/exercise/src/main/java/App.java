import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    private static final String GRINGOTTS_PU = "gringotts";
    private static final String SALES_PU = "sales";
    private static final String UNIVERSITY_SYSTEM_PU = "university";
    private static final String HOSPITAL_PU = "hospital";
    private static final String BILLS_PU = "bills";
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(UNIVERSITY_SYSTEM_PU);
        EntityManager entityManager = emf.createEntityManager();
        Engine engine = new Engine(entityManager);
        engine.run();
    }
}
