package com.whotere.rationplanner.representation.dto.form;

import com.whotere.rationplanner.representation.dto.EntityIdReferenceDto;
import com.whotere.rationplanner.representation.dto.PhotoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreationForm {

    private String name;
    private String producer;
    private ProductQuantityInformation quantity;
    private Double price;
    private PhotoDto photo;

    @Getter
    @Setter
    public static class ProductQuantityInformation {

        private Double amount;
        private EntityIdReferenceDto measurementUnit;
    }
}
