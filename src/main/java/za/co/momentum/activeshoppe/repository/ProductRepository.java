package za.co.momentum.activeshoppe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.momentum.activeshoppe.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findByName(String name);

    Product findByCode(String code);

}
