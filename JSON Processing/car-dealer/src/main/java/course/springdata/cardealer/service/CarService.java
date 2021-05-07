package course.springdata.cardealer.service;

import java.io.IOException;

public interface CarService {
    void seedCars() throws Exception;
    String findByToyota();
}
