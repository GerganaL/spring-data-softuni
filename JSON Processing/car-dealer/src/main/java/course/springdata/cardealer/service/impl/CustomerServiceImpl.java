package course.springdata.cardealer.service.impl;

import com.google.gson.Gson;
import course.springdata.cardealer.domain.dto.importDtos.CustomerSeedDto;
import course.springdata.cardealer.domain.dto.export.CustomerExposeDto;
import course.springdata.cardealer.domain.enitity.Customer;
import course.springdata.cardealer.domain.repository.CustomerRepository;
import course.springdata.cardealer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMER_PATH = "src/main/resources/jsons/customers.json";

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, Gson gson) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedCustomers() throws IOException {
        String content = String.join("", Files.readAllLines(Path.of(CUSTOMER_PATH)));

        CustomerSeedDto[] customerSeedDtos = this.gson.fromJson(content, CustomerSeedDto[].class);

        for (CustomerSeedDto customerSeedDto : customerSeedDtos) {
            Customer customer = this.modelMapper.map(customerSeedDto, Customer.class);
            this.customerRepository.saveAndFlush(customer);
        }
    }

    @Override
    public String orderedCustomers() {
        Set<Customer> orderedCustomers = this.customerRepository.getAllByOrderByBirthDateAscYoungDriverAsc();

        List<CustomerExposeDto> toExport = new ArrayList<>();
        for (Customer orderedCustomer : orderedCustomers) {
            CustomerExposeDto customerExposeDto = this.modelMapper.map(orderedCustomer, CustomerExposeDto.class);
            toExport.add(customerExposeDto);
        }
        return this.gson.toJson(toExport);
    }
}
