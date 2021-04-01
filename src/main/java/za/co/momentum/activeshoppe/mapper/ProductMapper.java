package za.co.momentum.activeshoppe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import za.co.momentum.activeshoppe.domain.Product;
import za.co.momentum.activeshoppe.model.ProductDTO;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target="name", source="name"),
            @Mapping(target="code", source="code"),
            @Mapping(target="cost", source="cost")
    })
    ProductDTO productToProductDTO(Product product);

    @Mappings({
            @Mapping(target="name", source="name"),
            @Mapping(target="code", source="code"),
            @Mapping(target="cost", source="cost")
    })
    Product productDTOToProduct(ProductDTO productDTO);
}
