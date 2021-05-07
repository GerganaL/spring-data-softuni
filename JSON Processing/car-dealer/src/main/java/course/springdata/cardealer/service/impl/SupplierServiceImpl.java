package course.springdata.cardealer.service.impl;

import com.google.gson.Gson;
import course.springdata.cardealer.domain.dto.importDtos.SupplierSeedDto;
import course.springdata.cardealer.domain.enitity.Supplier;
import course.springdata.cardealer.domain.repository.SupplierRepository;
import course.springdata.cardealer.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SupplierServiceImpl implements SupplierService {
    private static  final String SUPPLIER_PATH = "src/main/resources/jsons/suppliers.json";
    private final SupplierRepository supplierRepo;
    private final ModelMapper modelMapper;
    private final Gson gson;


    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepo, ModelMapper modelMapper, Gson gson) {
        this.supplierRepo = supplierRepo;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedSupplier() throws IOException {
        //READ JSON
     //   String content = String.valueOf(Files.readAllLines(Path.of(SUPPLIER_PATH)));
        String content = String.join("",Files.readAllLines(Path.of(SUPPLIER_PATH)));


        //JSON -> DTO
        SupplierSeedDto[] supplierSeedDtos = this.gson.fromJson(content, SupplierSeedDto[].class);

        // DTO save DB
        for (SupplierSeedDto supplierSeedDto : supplierSeedDtos) {
            this.supplierRepo.saveAndFlush(this.modelMapper.map(supplierSeedDto, Supplier.class));
        }
    }
}
