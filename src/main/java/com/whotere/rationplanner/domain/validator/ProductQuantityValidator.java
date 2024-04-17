package com.whotere.rationplanner.domain.validator;

import com.whotere.rationplanner.data.model.ProductQuantity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductQuantityValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return ProductQuantity.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {

        ProductQuantity productQuantity = (ProductQuantity) target;

        validateAmount(productQuantity, errors);
    }

    public void validateAmount(@NonNull ProductQuantity productQuantity, @NonNull Errors errors) {

        Double amount = productQuantity.getAmount();

        if(amount == null) {
            errors.rejectValue(
                    "amount",
                    "amount.invalid",
                    "Вы должны указать количество продукта"
            );
            return;
        }

        if(amount <= 0) {
            errors.rejectValue(
                    "amount",
                    "amount.invalid",
                    "Количество продукта не должно быть отрицательным или равным нулю"
            );
        }
    }
}
