package za.co.momentum.activeshoppe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import za.co.momentum.activeshoppe.domain.Customer;
import za.co.momentum.activeshoppe.domain.Product;
import za.co.momentum.activeshoppe.mapper.ProductMapper;
import za.co.momentum.activeshoppe.model.CustomerDTO;
import za.co.momentum.activeshoppe.model.ProductDTO;
import za.co.momentum.activeshoppe.repository.ProductRepository;
import za.co.momentum.activeshoppe.service.CustomerService;
import za.co.momentum.activeshoppe.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private CustomerService customerService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              CustomerService customerService) {
        this.productRepository = productRepository;
        this.customerService = customerService;
    }

    public List<ProductDTO> findAll() {
        List<Product> products = new ArrayList<>();
        this.productRepository.findAll().forEach(products::add);
        return products.stream().map(ProductMapper.INSTANCE::productToProductDTO).collect(Collectors.toList());
    }

    public ProductDTO findByCode(String code) {
        Product product = this.productRepository.findByCode(code);
        return ProductMapper.INSTANCE.productToProductDTO(product);
    }

    public void purchaseProducts(Integer customerId, List<String> productCodes) {
        CustomerDTO customer = this.customerService.findById(customerId);
        if (productCodes == null || productCodes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No product codes supplied");
        }

        long totalCost = 0;
        for (String productCode: productCodes) {
            ProductDTO product = this.findByCode(productCode);
            if (product == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product code %s not found", productCode));
            }
            totalCost = totalCost + product.getCost();
        }

        if (totalCost > customer.getActiveDaysPoints()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Customer (Total customer points: %d) does not have enough points for purchase (Required points: %d)", customer.getActiveDaysPoints(), totalCost));
        }

        customer.setActiveDaysPoints(customer.getActiveDaysPoints() - totalCost);
        this.customerService.saveCustomer(customer);
    }
}
