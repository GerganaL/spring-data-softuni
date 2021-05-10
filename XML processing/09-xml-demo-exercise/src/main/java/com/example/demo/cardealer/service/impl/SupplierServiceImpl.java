package com.example.demo.cardealer.service.impl;

import com.example.demo.cardealer.domain.dto.importDtos.SupplierImportDto;
import com.example.demo.cardealer.domain.dto.importDtos.SupplierImportRootDto;
import com.example.demo.cardealer.domain.enitity.Supplier;
import com.example.demo.cardealer.domain.repository.SupplierRepository;
import com.example.demo.cardealer.service.SupplierService;
import com.example.demo.cardealer.utils.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SupplierServiceImpl implements SupplierService {
    private static final String SUPPLIER_PATH = "src/main/resources/xmls/suppliers.xml";
    private final SupplierRepository supplierRepo;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;



    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepo, ModelMapper modelMapper, Gson gson, XmlParser xmlParser) {
        this.supplierRepo = supplierRepo;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedSupplier() throws IOException, JAXBException {

        SupplierImportRootDto supplierImportRootDto = this.xmlParser.parseXml(SupplierImportRootDto.class, SUPPLIER_PATH);

        for (SupplierImportDto supplier : supplierImportRootDto.getSuppliers()) {
            this.supplierRepo.save(this.modelMapper.map(supplier, Supplier.class));
        }
    }
}
