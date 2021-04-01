package za.co.momentum.activeshoppe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.momentum.activeshoppe.model.CustomerDTO;
import za.co.momentum.activeshoppe.model.ProductDTO;
import za.co.momentum.activeshoppe.model.PurchaseRequestDTO;
import za.co.momentum.activeshoppe.service.CustomerService;
import za.co.momentum.activeshoppe.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ShopController {

    private CustomerService customerService;

    private ProductService productService;

    @Autowired
    public ShopController(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return this.productService.findAll();
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers() {
        return this.customerService.findAll();
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseProducts(@RequestBody final PurchaseRequestDTO purchaseRequestDto) {
        this.productService.purchaseProducts(purchaseRequestDto.getCustomerId(), purchaseRequestDto.getProductCodes());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
