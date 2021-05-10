package com.example.demo.cardealer.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CustomerService {
    void seedCustomers() throws IOException, JAXBException;
    void exportOrdered() throws JAXBException;
}
