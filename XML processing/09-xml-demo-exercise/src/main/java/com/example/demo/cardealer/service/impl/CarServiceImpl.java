package com.example.demo.cardealer.service.impl;

import com.example.demo.cardealer.domain.dto.export.CarExportDto;
import com.example.demo.cardealer.domain.dto.export.CarExportRootDto;
import com.example.demo.cardealer.domain.dto.export.PartExportDto;
import com.example.demo.cardealer.domain.dto.export.PartExportRootDto;
import com.example.demo.cardealer.domain.dto.importDtos.CarImportDto;
import com.example.demo.cardealer.domain.dto.importDtos.CarImportRootDto;
import com.example.demo.cardealer.domain.enitity.Car;
import com.example.demo.cardealer.domain.enitity.Part;
import com.example.demo.cardealer.domain.repository.CarRepository;
import com.example.demo.cardealer.domain.repository.PartRepository;
import com.example.demo.cardealer.service.CarService;
import com.example.demo.cardealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.util.*;

@Service
public class CarServiceImpl implements CarService {

    private static final String CAR_PATH = "src/main/resources/xmls/cars.xml";
    private static final String CARS_AND_PARTS = "src/main/resources/exported/cars-and-parts.xml";

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final PartRepository partRepository;
    private final XmlParser xmlParser;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, PartRepository partRepository, XmlParser xmlParser) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;

        this.partRepository = partRepository;
        this.xmlParser = xmlParser;
    }

    @Override
    @Transactional
    public void seedCars() throws Exception {
        CarImportRootDto carImportRootDto = this.xmlParser.parseXml(CarImportRootDto.class, CAR_PATH);
        for (CarImportDto carDto : carImportRootDto.getCars()) {
            Car car = this.modelMapper.map(carDto, Car.class);
            car.setParts(this.getRandomParts());

            this.carRepository.saveAndFlush(car);
        }


    }

    @Override
    public void carsAndPart() throws JAXBException {
        List<Car> all = this.carRepository.findAll();

        List<CarExportDto> carExportDtos = new ArrayList<>();
        for (Car car : all) {
            CarExportDto carExportDto = this.modelMapper.map(car, CarExportDto.class);

            //all fields names need to be identical for mapper to map them if not this logic
//            List<PartExportDto> partExportDtos = new ArrayList<>();
//
//            for (Part part : car.getParts() ) {
//                PartExportDto partExportDto = this.modelMapper.map(part, PartExportDto.class);
//
//                partExportDtos.add(partExportDto);
//            }
//
//            PartExportRootDto partExportRootDto = new PartExportRootDto();
//            partExportRootDto.setParts(partExportDtos);
//            carExportDto.setParts(partExportRootDto);

            //parts
            carExportDtos.add(carExportDto);
        }

        CarExportRootDto carExportRootDto = new CarExportRootDto();
        carExportRootDto.setCarExportDtos(carExportDtos);

        this.xmlParser.exportToXml(carExportRootDto, CarExportRootDto.class,CARS_AND_PARTS );
    }


    private Set<Part> getRandomParts() throws Exception {
        Set<Part> parts = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            Part part = this.getRandomPart();
            parts.add(part);
        }
        return parts;
    }

    private Part getRandomPart() throws Exception {
        Random random = new Random();
        long index = (long) random.nextInt((int)this.partRepository.count()) + 1;
        Optional<Part> part = this.partRepository.findById(index);

        if(part.isPresent()){
            return part.get();
        }else {
            throw  new Exception("Invalid part id.");
        }
    }
}
