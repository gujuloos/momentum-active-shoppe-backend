package za.co.momentum.activeshoppe.service;

import za.co.momentum.activeshoppe.model.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO findByCode(String code);

    void purchaseProducts(Integer customerId, List<String> productCodes);
}
