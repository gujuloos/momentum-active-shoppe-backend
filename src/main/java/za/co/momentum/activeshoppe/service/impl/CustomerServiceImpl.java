package za.co.momentum.activeshoppe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import za.co.momentum.activeshoppe.domain.Customer;
import za.co.momentum.activeshoppe.mapper.CustomerMapper;
import za.co.momentum.activeshoppe.model.CustomerDTO;
import za.co.momentum.activeshoppe.repository.CustomerRepository;
import za.co.momentum.activeshoppe.service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> findAll() {
        List<Customer> customers = new ArrayList<>();
        this.customerRepository.findAll().forEach(customers::add);
        return customers.stream().map(CustomerMapper.INSTANCE::customerToCustomerDTO).collect(Collectors.toList());
    }

    public CustomerDTO findById(Integer id) {
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Customer with id %d not found", id)); // TODO: Return correct API code
        }
        return CustomerMapper.INSTANCE.customerToCustomerDTO(customer.get());
    }

    public CustomerDTO saveCustomer(CustomerDTO customer) {
        this.customerRepository.save(CustomerMapper.INSTANCE.customerDTOToCustomer(customer));
        return findById(customer.getCustomerId());
    }
}
