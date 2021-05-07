package course.springdata.cardealer.service.impl;

import com.google.gson.Gson;
import course.springdata.cardealer.domain.dto.export.CarExportDto;
import course.springdata.cardealer.domain.dto.importDtos.CarSeedDto;
import course.springdata.cardealer.domain.enitity.Car;
import course.springdata.cardealer.domain.enitity.Part;
import course.springdata.cardealer.domain.repository.CarRepository;
import course.springdata.cardealer.domain.repository.PartRepository;
import course.springdata.cardealer.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class CarServiceImpl implements CarService {

    private static final String CAR_PATH = "src/main/resources/jsons/cars.json";

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final PartRepository partRepository;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, Gson gson, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.partRepository = partRepository;
    }

    @Override
    @Transactional
    public void seedCars() throws Exception {
        String content = String.join("", Files.readAllLines(Path.of(CAR_PATH)));

        CarSeedDto[] carSeedDtos = this.gson.fromJson(content, CarSeedDto[].class);

        for (CarSeedDto carSeedDto : carSeedDtos) {
            Car car = this.modelMapper.map(carSeedDto,Car.class);
            car.setParts(getRandomParts());


            this.carRepository.saveAndFlush(car);
        }

    }

    @Override
    public String findByToyota() {
        Set<Car> toyota = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        List<CarExportDto> carExportDtos = new ArrayList<>();
        for (Car car : toyota) {
            CarExportDto carExportDto = this.modelMapper.map(car,CarExportDto.class);
            carExportDtos.add(carExportDto);
        }

        return this.gson.toJson(carExportDtos);
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
