package course.springdata.cardealer.service;

import java.io.IOException;

public interface CustomerService {
    void seedCustomers() throws IOException;
    String orderedCustomers();
}
