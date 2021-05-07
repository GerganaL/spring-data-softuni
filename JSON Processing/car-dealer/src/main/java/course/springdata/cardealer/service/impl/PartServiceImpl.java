package course.springdata.cardealer.service.impl;

import com.google.gson.Gson;
import course.springdata.cardealer.domain.dto.importDtos.PartSeedDto;
import course.springdata.cardealer.domain.enitity.Part;
import course.springdata.cardealer.domain.enitity.Supplier;
import course.springdata.cardealer.domain.repository.PartRepository;
import course.springdata.cardealer.domain.repository.SupplierRepository;
import course.springdata.cardealer.service.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private static final String PARTS_PATH = "src/main/resources/jsons/parts.json";
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final SupplierRepository supplierRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, Gson gson, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void seedParts() throws Exception {
        String content = String.join("", Files.readAllLines(Path.of(PARTS_PATH)));
        //JSON -> DTO
        PartSeedDto[] partSeedDtos = this.gson.fromJson(content, PartSeedDto[].class);
        // DTO save DB
        for (PartSeedDto seedDto : partSeedDtos) {
            Part part = this.modelMapper.map(seedDto, Part.class);
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
