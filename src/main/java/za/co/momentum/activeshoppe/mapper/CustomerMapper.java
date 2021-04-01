package za.co.momentum.activeshoppe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import za.co.momentum.activeshoppe.domain.Customer;
import za.co.momentum.activeshoppe.model.CustomerDTO;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
            @Mapping(target="customerId", source="id"),
            @Mapping(target="fullName", source="fullName"),
            @Mapping(target="activeDaysPoints", source="activeDaysPoints")
    })
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mappings({
            @Mapping(target="id", source="customerId"),
            @Mapping(target="fullName", source="fullName"),
            @Mapping(target="activeDaysPoints", source="activeDaysPoints")
    })
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
