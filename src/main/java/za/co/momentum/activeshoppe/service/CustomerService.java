package za.co.momentum.activeshoppe.service;

import za.co.momentum.activeshoppe.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> findAll();

    CustomerDTO findById(Integer id);

    CustomerDTO saveCustomer(CustomerDTO customer);
}
