package com.whotere.rationplanner.representation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private String id;
    private String name;
    private String producer;
    private ProductQuantityDto quantity;
    private Double price;
    private PhotoDto photo;
}
