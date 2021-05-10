package com.example.demo.cardealer.service;

import javax.xml.bind.JAXBException;

public interface SaleService {
    void seedSales();

    void salesDiscount() throws JAXBException;
}
