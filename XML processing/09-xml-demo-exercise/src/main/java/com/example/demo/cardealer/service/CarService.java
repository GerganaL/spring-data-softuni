package com.example.demo.cardealer.service;

import javax.xml.bind.JAXBException;

public interface CarService {
    void seedCars() throws Exception;
    void carsAndPart() throws JAXBException;
}
