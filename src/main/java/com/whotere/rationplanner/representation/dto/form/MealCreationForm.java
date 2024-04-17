package com.whotere.rationplanner.representation.dto.form;

import com.whotere.rationplanner.representation.dto.EntityIdReferenceDto;
import com.whotere.rationplanner.representation.dto.PhotoDto;
import com.whotere.rationplanner.representation.dto.TimeDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MealCreationForm {

    private String name;
    private String description;
    private String recipe;
    private TimeDto cookingDuration;
    private Set<IngredientInformation> ingredients;
    private PhotoDto photo;

    @Getter
    @Setter
    public static class IngredientInformation {

        private String name;
        private EntityIdReferenceDto product;
        private ProductQuantityInformation productQuantity;

        @Getter
        @Setter
        public static class ProductQuantityInformation {

            private Double amount;
            private EntityIdReferenceDto measurementUnit;
        }
    }
}
