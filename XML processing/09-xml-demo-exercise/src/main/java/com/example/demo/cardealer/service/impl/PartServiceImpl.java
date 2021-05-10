package com.example.demo.cardealer.service.impl;


import com.example.demo.cardealer.domain.dto.importDtos.PartImportDto;
import com.example.demo.cardealer.domain.dto.importDtos.PartImportRootDto;
import com.example.demo.cardealer.domain.enitity.Part;
import com.example.demo.cardealer.domain.enitity.Supplier;
import com.example.demo.cardealer.domain.repository.PartRepository;
import com.example.demo.cardealer.domain.repository.SupplierRepository;
import com.example.demo.cardealer.service.PartService;
import com.example.demo.cardealer.utils.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private static final String PARTS_PATH = "src/main/resources/xmls/parts.xml";
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;
    private final XmlParser xmlParser;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, Gson gson, SupplierRepository supplierRepository, XmlParser xmlParser) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void seedParts() throws Exception {
        PartImportRootDto partImportRootDto = this.xmlParser.parseXml(PartImportRootDto.class, PARTS_PATH);

        for (PartImportDto partDto : partImportRootDto.getParts()) {
            Part part = this.modelMapper.map(partDto,Part.class) ;
            part.setSupplier(getRandomSupplier());

            this.partRepository.saveAndFlush(part);
        }

    }

    private Supplier getRandomSupplier() throws Exception {
        Random random = new Random();
       long index = (long) random.nextInt((int) this.supplierRepository.count()) + 1;
       Optional<Supplier> supplier = this.supplierRepository.findById(index);

       if(supplier.isPresent()){
           return supplier.get();
       }else {
           throw new Exception("Supplier does not exist");
       }
    }
}
