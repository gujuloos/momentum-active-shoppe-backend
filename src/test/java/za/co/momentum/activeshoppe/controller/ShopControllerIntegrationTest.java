package za.co.momentum.activeshoppe.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.momentum.activeshoppe.Application;
import za.co.momentum.activeshoppe.model.ProductDTO;
import za.co.momentum.activeshoppe.model.PurchaseRequestDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShopControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Sql({"classpath:data.sql"})
    @Test
    public void testAllEmployees() {
        ResponseEntity<ProductDTO[]> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/momentum-active-shoppe/api/v1/products", ProductDTO[].class);
        ProductDTO[] productArray = responseEntity.getBody();
        List<ProductDTO> productList = Arrays.stream(productArray).collect(Collectors.toList());
        Assert.assertEquals(7, productList.size());
    }

    @Sql({"classpath:data.sql"})
    @Test
    public void testPurchaseProduct() {
        PurchaseRequestDTO purchaseRequestDto = new PurchaseRequestDTO();
        purchaseRequestDto.setCustomerId(1);
        purchaseRequestDto.setProductCodes(Collections.singletonList("XSzPjaPhDF"));

        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/momentum-active-shoppe/api/v1/purchase", purchaseRequestDto, String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
