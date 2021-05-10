package com.example.demo.cardealer.service.impl;
import com.example.demo.cardealer.domain.dto.export.lastExport.CarLastExportDto;
import com.example.demo.cardealer.domain.dto.export.lastExport.SaleExportDto;
import com.example.demo.cardealer.domain.dto.export.lastExport.SaleExportRootDto;
import com.example.demo.cardealer.domain.enitity.Car;
import com.example.demo.cardealer.domain.enitity.Customer;
import com.example.demo.cardealer.domain.enitity.Sale;
import com.example.demo.cardealer.domain.repository.CarRepository;
import com.example.demo.cardealer.domain.repository.CustomerRepository;
import com.example.demo.cardealer.domain.repository.SaleRepository;
import com.example.demo.cardealer.service.SaleService;
import com.example.demo.cardealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final static String SALE_DISCOUNT_PATH = "src/main/resources/exported/sales-discounts.xml";

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedSales() {
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

    @Override
    public void salesDiscount() throws JAXBException {
        SaleExportRootDto saleExportRootDto = new SaleExportRootDto();
        List<SaleExportDto> saleExportDtos = new ArrayList<>();

        for (Sale sale : this.saleRepository.findAll()) {
            SaleExportDto saleExportDto = this.modelMapper.map(sale,SaleExportDto.class);
            saleExportDto.setDiscount(saleExportDto.getDiscount()/100);
            //saleExportDto.setCar(this.modelMapper.map(sale.getCar(), CarLastExportDto.class));

            double totalPrice = sale.getCar().getParts().stream().mapToDouble(p -> Double.parseDouble(p.getPrice().toString())).sum();
            saleExportDto.setPrice(BigDecimal.valueOf(totalPrice));
            double priceWithDiscount = totalPrice - (totalPrice * sale.getDiscount() * 1.0 / 100);
            saleExportDto.setPriceWithDiscount(BigDecimal.valueOf(priceWithDiscount));

            saleExportDtos.add(saleExportDto);
        }

        saleExportRootDto.setSales(saleExportDtos);
        this.xmlParser.exportToXml(saleExportRootDto,SaleExportRootDto.class,SALE_DISCOUNT_PATH);

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
