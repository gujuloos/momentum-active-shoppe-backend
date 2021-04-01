package za.co.momentum.activeshoppe.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import za.co.momentum.activeshoppe.domain.Customer;
import za.co.momentum.activeshoppe.model.CustomerDTO;
import za.co.momentum.activeshoppe.repository.CustomerRepository;
import za.co.momentum.activeshoppe.service.impl.CustomerServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void before() {
        this.customerService = new CustomerServiceImpl(this.customerRepository);
    }

    @Test
    public void findByIdTest() {
        when(customerRepository.findById(eq(1))).thenReturn(Optional.of(getCustomer(100)));
        CustomerDTO customer = this.customerService.findById(1);
        Assert.assertEquals(1, customer.getCustomerId());
        Assert.assertEquals("Bruce Wayne", customer.getFullName());
        Assert.assertEquals(100, customer.getActiveDaysPoints());
    }

    @Test
    public void findByIdCustomerNotFoundTest() {
        when(customerRepository.findById(eq(1))).thenReturn(Optional.empty());
        try {
            CustomerDTO customer = this.customerService.findById(1);
        } catch (Exception ex) {
            Assert.assertEquals("404 NOT_FOUND \"Customer with id 1 not found\"", ex.getMessage());
            Assert.assertTrue(ex instanceof ResponseStatusException);
        }
    }

    @Test
    public void saveCustomerTest() {
        when(customerRepository.findById(eq(1))).thenReturn(Optional.of(getCustomer(100)));
        when(customerRepository.save(any(Customer.class))).thenReturn(getCustomer(50));
        CustomerDTO customer = this.customerService.findById(1);
        Assert.assertEquals(1, customer.getCustomerId());
        Assert.assertEquals("Bruce Wayne", customer.getFullName());
        Assert.assertEquals(100, customer.getActiveDaysPoints());

        customer = this.customerService.saveCustomer(customer);
        Assert.assertEquals(100, customer.getActiveDaysPoints());
    }

    private Customer getCustomer(int points) {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFullName("Bruce Wayne");
        customer.setActiveDaysPoints(points);
        return customer;
    }
}
