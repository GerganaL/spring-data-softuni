package com.example.demo.cardealer.service.impl;


import com.example.demo.cardealer.domain.dto.export.CustomerOrderExportDto;
import com.example.demo.cardealer.domain.dto.export.CustomerOrderExportRootDto;
import com.example.demo.cardealer.domain.dto.importDtos.CustomerImportDto;
import com.example.demo.cardealer.domain.dto.importDtos.CustomerImportRootDto;
import com.example.demo.cardealer.domain.enitity.Customer;
import com.example.demo.cardealer.domain.repository.CustomerRepository;
import com.example.demo.cardealer.service.CustomerService;
import com.example.demo.cardealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMER_PATH = "src/main/resources/xmls/customers.xml";
    private static final String ORDERED_PATH = "src/main/resources/exported/ordered-customers.xml";

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedCustomers() throws IOException, JAXBException {
        String content = String.join("", Files.readAllLines(Path.of(CUSTOMER_PATH)));


        CustomerImportRootDto customerImportRootDto = this.xmlParser.parseXml(CustomerImportRootDto.class, CUSTOMER_PATH);

        for (CustomerImportDto customerDto : customerImportRootDto.getCustomers()) {
            this.customerRepository.saveAndFlush(this.modelMapper.map(customerDto, Customer.class));
        }
    }

    @Override
    public void exportOrdered() throws JAXBException {
        List<CustomerOrderExportDto> dtos = this.customerRepository.findAllAndSort()
                .stream()
                .map(c -> this.modelMapper.map(c, CustomerOrderExportDto.class))
                .collect(Collectors.toList());

        CustomerOrderExportRootDto rootDto = new CustomerOrderExportRootDto();
        rootDto.setCustomers(dtos);

        this.xmlParser.exportToXml(rootDto, CustomerOrderExportRootDto.class, ORDERED_PATH);
    }


}
