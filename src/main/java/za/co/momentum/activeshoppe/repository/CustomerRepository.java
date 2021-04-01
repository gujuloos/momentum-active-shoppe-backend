package za.co.momentum.activeshoppe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.momentum.activeshoppe.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
