import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class Engine implements Runnable {
    private final EntityManager manager;
    private final BufferedReader reader;

    public Engine(EntityManager manager) {
        this.manager = manager;
        reader = new BufferedReader(new InputStreamReader(System.in));

    }

    @Override
    public void run() {

        // 2.Change casing
        //  changerCasingEx2();

        // 3.Contains Employee
//        try {
//            containsEmployeeEx3();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //4.Employees with Salary Over 50 000
        // employeesWithSalaryOverFiftyThousandEx4();

        //5.Employees from Department
        // employeesFromDepartmentEx5();

        //6.Adding a New Address and Updating Employee
//        try {
//            addingNewAddressAndUpdateEmployeeEx6();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //7.Addresses with Employee Count
         addressesWithEmployeeCount();

        //8.Get Employee with Project
//        try {
//            getEmployeeWithProject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //9.Find Latest 10 Projects
       // findLatest10Projects();

        //10.Increase Salaries
        //increaseSalaries();

        //11.Find Employees by First Name
//        try {
//            findEmployeesByFirstName();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //12. Employees Maximum Salaries
        //employeesMaximumSalaries();

    }

    private void employeesMaximumSalaries() {
//        manager.createQuery("SELECT e FROM  Employee e " +
//                "WHERE e.salary NOT BETWEEN 30000 AND 70000 " +
//                "GROUP BY e.department.name " +
//                "ORDER BY e.salary DESC", Employee.class)
//                .getResultStream()
//                .sorted(Comparator.comparing(e -> e.getDepartment().getId()))
//                .forEach(employee -> System.out.printf("%s - %.2f%n",employee.getDepartment().getName(),employee.getSalary()));

        StringBuilder employees = new StringBuilder();
        manager
                .createQuery("SELECT e FROM  Employee AS e " +
                        "WHERE e.salary NOT BETWEEN 30000 AND 70000 " +
                        "GROUP BY e.department.name " +
                        "ORDER BY e.salary DESC", Employee.class)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(e -> e.getDepartment().getId()))
                .forEach(employee -> employees.append(String.format("%s - %.2f%n",
                        employee.getDepartment().getName(), employee.getSalary())));

        manager.close();
        System.out.println(employees.toString().trim());

    }

    private void findEmployeesByFirstName() throws IOException {
        System.out.println("Enter valid Employee First Name: ");
        String employeeName = reader.readLine();

        manager.createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE :name",Employee.class)
                .setParameter("name", employeeName + "%")
                .getResultStream().forEach(employee ->{
            System.out.printf("%s %s - %s - ($%.2f)%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getJobTitle(),
                    employee.getSalary());
        });
    }

    private void findLatest10Projects() {
        manager.createQuery("SELECT p FROM Project p ORDER BY p.startDate DESC ", Project.class)
                .setMaxResults(10)
                .getResultStream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> {
                    System.out.printf("Project Name: %s%n\tProject Description: %s%n\tProject Start Date: %s%n\tProject End Date: %s%n",
                            project.getName(),
                            project.getDescription(),
                            project.getStartDate(),
                            project.getEndDate());
                });
    }

    private void increaseSalaries() {
        manager.getTransaction().begin();
        int affectedRows = manager.createQuery("UPDATE Employee e SET e.salary = e.salary * 1.12 WHERE e.department.id IN(1,2,4,11)").executeUpdate();
        manager.getTransaction().commit();

        System.out.println("Affected rows: " + affectedRows);
        manager.createQuery("SELECT e FROM Employee e WHERE e.department.id IN(1,2,4,11)", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s ($%.2f)%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getSalary()));
    }

    private void getEmployeeWithProject() throws IOException {
        System.out.println("Enter valid employee id: ");
        int id = Integer.parseInt(reader.readLine());

        Employee employee = manager.find(Employee.class, id);
        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        employee.getProjects().stream().sorted(Comparator.comparing(Project::getName)).forEach(p -> System.out.printf("\t%s%n", p.getName()));

    }

    private void addressesWithEmployeeCount() {
        //data is dirty size is 2 not 3
        List<Address> addresses = manager
                .createQuery("SELECT a FROM Address a " +
                        "ORDER BY a.employees.size DESC ", Address.class)
                .setMaxResults(10)
                .getResultList();

        addresses.forEach(address -> System.out.printf("%s, %s - %d%n", address.getText(), address.getTown().getName(), address.getEmployees().size()));
    }

    private void addingNewAddressAndUpdateEmployeeEx6() throws IOException {
        Address address = createAddress("Vitoshka 15");
        System.out.println("Enter employee last name:");
        String lastName = reader.readLine();

        Employee employee = manager.createQuery("SELECT e FROM Employee e WHERE e.lastName = :name", Employee.class)
                .setParameter("name", lastName).getSingleResult();
        manager.getTransaction().begin();
        employee.setAddress(address);
        manager.getTransaction().commit();
    }

    private Address createAddress(String addressText) {
        Address address = new Address();
        address.setText(addressText);

        manager.getTransaction().begin();
        manager.persist(address);
        manager.getTransaction().commit();
        return address;
    }


    private void employeesFromDepartmentEx5() {
        manager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.department.name = 'Research and Development' " +
                "ORDER BY e.salary, e.id", Employee.class)
                .getResultStream()
                .forEach(e -> {
                    System.out.printf("%s %s from Research and Development - $%.2f%n",
                            e.getFirstName(), e.getLastName(),
                            e.getSalary());
                });
    }

    private void employeesWithSalaryOverFiftyThousandEx4() {
//        List<Employee> employees = manager.createQuery("SELECT e FROM Employee e " +
//                "WHERE e.salary > 50000",Employee.class)
//                .getResultList();

        manager.createQuery("SELECT  e FROM Employee e " +
                "WHERE e.salary > 50000", Employee.class)
                .getResultList()
                .stream().map(Employee::getFirstName)
                .forEach(System.out::println);

    }

    private void containsEmployeeEx3() throws IOException {
        System.out.println("Enter employee full:");
        String fullName = reader.readLine();
        List<Employee> employees = manager.createQuery("select e FROM Employee e " +
                "WHERE concat(e.firstName, ' ', e.lastName) = :name", Employee.class)
                .setParameter("name", fullName)
                .getResultList();

        System.out.println(employees.isEmpty() ? "NO" : "YES");
    }

    public void changerCasingEx2() {
        List<Town> towns = manager
                .createQuery("SELECT t FROM Town as t WHERE length(t.name) > 5 ", Town.class)
                .getResultList();
        manager.getTransaction().begin();
        towns.forEach(manager::detach);
        for (Town town : towns) {
            town.setName(town.getName().toLowerCase());
        }
        towns.forEach(manager::merge);
        manager.flush();
        manager.getTransaction().commit();
    }
}
