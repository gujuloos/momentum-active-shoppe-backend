package za.co.momentum.activeshoppe.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import za.co.momentum.activeshoppe.domain.Customer;
import za.co.momentum.activeshoppe.domain.Product;
import za.co.momentum.activeshoppe.model.CustomerDTO;
import za.co.momentum.activeshoppe.model.ProductDTO;
import za.co.momentum.activeshoppe.repository.ProductRepository;
import za.co.momentum.activeshoppe.service.impl.ProductServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerService customerService;

    private ProductService productService;

    @Before
    public void before() {
        this.productService = new ProductServiceImpl(productRepository, customerService);
    }

    @Test
    public void findAllTest() {
        when(productRepository.findAll()).thenReturn(getProductList());
        List<ProductDTO> productList = this.productService.findAll();
        Assert.assertEquals(2, productList.size());
    }

    @Test
    public void findByCodeTest() {
        when(productRepository.findByCode(eq("P001"))).thenReturn(getProductList().get(0));
        ProductDTO product = this.productService.findByCode("P001");
        Assert.assertEquals("First Product", product.getName());
        Assert.assertEquals("P001", product.getCode());
        Assert.assertEquals(100, product.getCost());
    }

    @Test
    public void purchaseProductsNoCustomerFoundTest() {
        when(customerService.findById(eq(1))).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Customer with id %d not found", 1)));
        try {
            this.productService.purchaseProducts(1, Collections.singletonList("P001"));
        } catch (Exception ex) {
            Assert.assertEquals("404 NOT_FOUND \"Customer with id 1 not found\"", ex.getMessage());
            Assert.assertTrue(ex instanceof ResponseStatusException);
        }
    }

    @Test
    public void purchaseProductsNoProductCodesSuppliedTest() {
        when(customerService.findById(eq(1))).thenReturn(getCustomer(100));
        try {
            this.productService.purchaseProducts(1, null);
        } catch (Exception ex) {
            Assert.assertEquals("400 BAD_REQUEST \"No product codes supplied\"", ex.getMessage());
            Assert.assertTrue(ex instanceof ResponseStatusException);
        }
    }

    @Test
    public void purchaseProductsInvalidProductCodeTest() {
        when(customerService.findById(eq(1))).thenReturn(getCustomer(100));
        when(productRepository.findByCode(eq("P001"))).thenReturn(getProductList().get(0));
        when(productRepository.findByCode(eq("P002"))).thenReturn(getProductList().get(1));
        try {
            this.productService.purchaseProducts(1, Arrays.asList("P001", "P003"));
        } catch (Exception ex) {
            Assert.assertEquals("404 NOT_FOUND \"Product code P003 not found\"", ex.getMessage());
            Assert.assertTrue(ex instanceof ResponseStatusException);
        }
    }

    @Test
    public void purchaseProductsCustomerNotEnoughPointsTest() {
        when(customerService.findById(eq(1))).thenReturn(getCustomer(100));
        when(productRepository.findByCode(eq("P001"))).thenReturn(getProductList().get(0));
        when(productRepository.findByCode(eq("P002"))).thenReturn(getProductList().get(1));
        try {
            this.productService.purchaseProducts(1, Arrays.asList("P001", "P002"));
        } catch (Exception ex) {
            Assert.assertEquals("400 BAD_REQUEST \"Customer (Total customer points: 100) does not have enough points for purchase (Required points: 325)\"", ex.getMessage());
            Assert.assertTrue(ex instanceof ResponseStatusException);
        }
    }

    @Test
    public void purchaseProductsTest() {
        when(customerService.findById(eq(1))).thenReturn(getCustomer(1000));
        when(productRepository.findByCode(eq("P001"))).thenReturn(getProductList().get(0));
        when(productRepository.findByCode(eq("P002"))).thenReturn(getProductList().get(1));
        this.productService.purchaseProducts(1, Arrays.asList("P001", "P002"));
        verify(customerService, times(1)).saveCustomer(any(CustomerDTO.class));
    }

    private List<Product> getProductList() {
        List<Product> products = new ArrayList<>();

        Product firstProduct = new Product();
        firstProduct.setId(1);
        firstProduct.setName("First Product");
        firstProduct.setCode("P001");
        firstProduct.setCost(100);

        Product secondProduct = new Product();
        secondProduct.setId(2);
        secondProduct.setName("Second Product");
        secondProduct.setCode("P002");
        secondProduct.setCost(225);

        products.add(firstProduct);
        products.add(secondProduct);

        return products;
    }

    private CustomerDTO getCustomer(int points) {
        CustomerDTO customer = new CustomerDTO();
        customer.setCustomerId(1);
        customer.setFullName("Bruce Wayne");
        customer.setActiveDaysPoints(points);
        return customer;
    }
}
