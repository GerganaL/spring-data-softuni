import entities.gringots.WizzardDeposit;

import javax.persistence.EntityManager;

public class Engine implements Runnable{
    private EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void run() {
        WizzardDeposit wd = new WizzardDeposit();

        wd.setLast_name("Pesho");
        wd.setAge(10);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(wd);
        this.entityManager.getTransaction().commit();
    }
}
