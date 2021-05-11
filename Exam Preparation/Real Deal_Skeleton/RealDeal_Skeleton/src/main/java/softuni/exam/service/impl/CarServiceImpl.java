package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Car;
import softuni.exam.models.dto.jsons.CarImportDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {

    private static final String CAR_PATH = "src/main/resources/files/json/cars.json";

    private final CarRepository carRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(CAR_PATH)));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder sb = new StringBuilder();

        CarImportDto[] carImportDtos = this.gson.fromJson(this.readCarsFileContent(), CarImportDto[].class);

        for (CarImportDto carDto : carImportDtos) {
           if(this.validationUtil.isValid(carDto)){
               this.carRepository.saveAndFlush(modelMapper.map(carDto, Car.class));
                sb.append(String.format("Successfully imported car - %s - %s",carDto.getMake(),carDto.getModel()));
                sb.append(System.lineSeparator());
           }else {
               sb.append("Invalid car").append(System.lineSeparator());
           }
        }

        return sb.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        StringBuilder sb = new StringBuilder();

        Set<Car> cars = this.carRepository.exportCars();
        for (Car car : cars) {
            sb.append(String.format("Car make - %s, model - %s%n\tKilometers - %s%n\tRegistered on - %s%n\tNumber of pictures - %d",
                    car.getMake(),
                    car.getModel(),
                    car.getKilometers(),
                    car.getRegisteredOn(),
                    car.getPictures().size()));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
