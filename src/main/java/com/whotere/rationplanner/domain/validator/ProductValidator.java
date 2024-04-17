package com.whotere.rationplanner.domain.validator;

import com.whotere.rationplanner.data.model.Photo;
import com.whotere.rationplanner.data.model.Product;
import com.whotere.rationplanner.data.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ProductValidator implements Validator {

    private final PhotoRepository photoRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {

        Product product = (Product) target;

        validateName(product, errors);
        validateProducer(product, errors);
        validatePrice(product, errors);
        validatePhoto(product, errors);
    }

    public void validateName(@NonNull Product product, @NonNull Errors errors) {

        String name = product.getName();

        if (name == null) {
            errors.rejectValue(
                    "name",
                    "name.invalid",
                    "Название продукта не должно быть пустым"
            );
            return;
        }

        if(2 > name.length() || name.length() > 50) {
            errors.rejectValue(
                    "name",
                    "name.invalid",
                    "Название продукта должно иметь длину от 2 до 50 символов"
            );
        }
    }

    public void validateProducer(@NonNull Product product, @NonNull Errors errors) {

        String producer = product.getProducer();

        if(producer != null) {

            if(1 > producer.length() || producer.length() > 50) {
                errors.rejectValue(
                        "producer",
                        "producer.invalid",
                        "Имя производителя должно иметь длину от 2 до 50 символов"
                );
            }
        }
    }

    public void validatePrice(@NonNull Product product, @NonNull Errors errors) {

        Double price = product.getPrice();

        if(price == null) {
            errors.rejectValue(
                    "price",
                    "price.invalid",
                    "Вы должны указать цену продукта"
            );
            return;
        }

        if(price <= 0) {
            errors.rejectValue(
                    "price",
                    "price.invalid",
                    "Цена продукта должна быть больше нуля"
            );
        }
    }

    public void validatePhoto(@NonNull Product product, @NonNull Errors errors) {

        Photo photo = product.getPhoto();

        if(photo == null) return;

        if(photo.getId() == null) {
            errors.rejectValue(
                    "photo",
                    "photo.invalid",
                    "Данные о фотографии продукта заполнены неверно"
            );
        }

        if(!photoRepository.existsById(photo.getId())) {
            errors.rejectValue(
                    "photo",
                    "photo.invalid",
                    String.format("Фотография с id [%s] не существует", photo.getId())
            );
        }
    }
}
