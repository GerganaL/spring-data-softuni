package course.springdata.cardealer.service.impl;

import course.springdata.cardealer.domain.enitity.Car;
import course.springdata.cardealer.domain.enitity.Customer;
import course.springdata.cardealer.domain.enitity.Sale;
import course.springdata.cardealer.domain.repository.CarRepository;
import course.springdata.cardealer.domain.repository.CustomerRepository;
import course.springdata.cardealer.domain.repository.SaleRepository;
import course.springdata.cardealer.service.SaleService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void seedService() {
        Sale sale = new Sale();
        sale.setCustomer(getRandomCustomer());
        sale.setCar(getRandomCar());
        sale.setDiscount(5);

        Sale sale1 = new Sale();
        sale1.setCustomer(getRandomCustomer());
        sale1.setCar(getRandomCar());
        sale1.setDiscount(10);

        Sale sale2 = new Sale();
        sale2.setCustomer(getRandomCustomer());
        sale2.setCar(getRandomCar());
        sale2.setDiscount(30);

        this.saleRepository.saveAndFlush(sale);
        this.saleRepository.saveAndFlush(sale1);
        this.saleRepository.saveAndFlush(sale2);
    }

    private Car getRandomCar() {
        Random random = new Random();
        long id = (long) random.nextInt((int)this.carRepository.count()) + 1;
        Car car = this.carRepository.findById(id).get();
        return car;
    }

    private Customer getRandomCustomer() {
        Random random = new Random();
        long id = (long) random.nextInt((int)this.customerRepository.count()) + 1;
        Customer customer = this.customerRepository.findById(id).get();
        return customer;
    }
}
