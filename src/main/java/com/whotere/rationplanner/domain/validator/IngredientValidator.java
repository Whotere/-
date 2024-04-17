package com.whotere.rationplanner.domain.validator;

import com.whotere.rationplanner.data.model.Ingredient;
import com.whotere.rationplanner.data.model.Product;
import com.whotere.rationplanner.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class IngredientValidator implements Validator {

    private final ProductRepository productRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Ingredient.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {

        Ingredient ingredient = (Ingredient) target;

        validateName(ingredient, errors);
        validateProduct(ingredient, errors);
    }

    public void validateName(@NonNull Ingredient ingredient, @NonNull Errors errors) {

        String name = ingredient.getName();

        if (name == null) {
            errors.rejectValue(
                    "name",
                    "name.invalid",
                    "Название ингредиента не должно быть пустым"
            );
            return;
        }

        if(name.length() < 2 || name.length() > 50) {
            errors.rejectValue(
                    "name",
                    "name.invalid",
                    "Название ингредиента должно иметь длину от 2 до 50 символов"
            );
        }
    }

    public void validateProduct(@NonNull Ingredient ingredient, @NonNull Errors errors) {

        Product product = ingredient.getProduct();

        if(product == null) return;

        if(product.getId() == null || !productRepository.existsById(product.getId())) {
            errors.rejectValue(
                    "product",
                    "product.invalid",
                    "Продукт, выбранный для одного из ингридиентов блюда, не существует"
            );
        }
    }
}
