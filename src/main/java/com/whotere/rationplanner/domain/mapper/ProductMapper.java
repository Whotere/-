package com.whotere.rationplanner.domain.mapper;

import com.whotere.rationplanner.data.model.Product;
import com.whotere.rationplanner.representation.dto.ProductDto;
import com.whotere.rationplanner.representation.dto.form.ProductCreationForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toProductDto(Product product);

    Product toProduct(ProductCreationForm productCreationForm);
}
