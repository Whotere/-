package com.whotere.rationplanner.domain.validator;

import com.whotere.rationplanner.data.model.Meal;
import com.whotere.rationplanner.data.model.Photo;
import com.whotere.rationplanner.data.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MealValidator implements Validator {

    private final PhotoRepository photoRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Meal.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {

        Meal meal = (Meal) target;

        validateName(meal, errors);
        validateDescription(meal, errors);
        validateCookingDuration(meal, errors);
        validateRecipe(meal, errors);
        validatePhoto(meal, errors);
    }

    public void validateName(@NonNull Meal meal, @NonNull Errors errors) {

        String name = meal.getName();

        if (name == null) {
            errors.rejectValue(
                    "name",
                    "name.invalid",
                    "Название блюда не должно быть пустым"
            );
            return;
        }

        if(2 > name.length() || name.length() > 50) {
            errors.rejectValue(
                    "name",
                    "name.invalid",
                    "Название блюда должно иметь длину от 2 до 50 символов"
            );
        }
    }

    public void validateDescription(@NonNull Meal meal, @NonNull Errors errors) {

        String description = meal.getDescription();

        if(description == null) {
            return;
        }

        if(1 > description.length() || description.length() > 250) {
            errors.rejectValue(
                    "description",
                    "description.invalid",
                    "Описание блюда должно иметь длину от 1 до 255 символов"
            );
        }
    }

    public void validateCookingDuration(@NonNull Meal meal, @NonNull Errors errors) {

        if(meal.getCookingDuration() == null) {
            errors.rejectValue(
                    "cookingDuration",
                    "cookingDuration.invalid",
                    "Время приготовления блюда не указано или указано неверно"
            );
        }
    }

    public void validateRecipe(@NonNull Meal meal, @NonNull Errors errors) {

        String recipe = meal.getRecipe();

        if(recipe == null || recipe.equals("")) {
            return;
        }

        if(1 > recipe.length() || recipe.length() > 10000) {
            errors.rejectValue(
                    "recipe",
                    "recipe.invalid",
                    "Описание рецепта блюда не должно быть длиннее 10000 символов"
            );
        }
    }

    public void validatePhoto(@NonNull Meal meal, @NonNull Errors errors) {

        Photo photo = meal.getPhoto();

        if(photo == null) return;

        if(photo.getId() == null) {
            errors.rejectValue(
                    "photo",
                    "photo.invalid",
                    "Данные о фотографии блюда заполнены неверно"
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
