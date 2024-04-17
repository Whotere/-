package com.whotere.rationplanner.domain.service.impl;

import com.whotere.rationplanner.data.model.Meal;
import com.whotere.rationplanner.data.model.User;
import com.whotere.rationplanner.data.repository.MealRepository;
import com.whotere.rationplanner.domain.exception.AccessDeniedException;
import com.whotere.rationplanner.domain.exception.EntityNotFoundException;
import com.whotere.rationplanner.domain.service.MealService;
import com.whotere.rationplanner.domain.service.PrincipalService;
import com.whotere.rationplanner.domain.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final PrincipalService principalService;

    private final MealRepository mealRepository;
    private final EntityManager entityManager;

    private final ValidationService validationService;
    private final Validator mealValidator;
    private final Validator ingredientValidator;

    @Override
    @Transactional
    public Meal createMeal(@NonNull Meal mealData) {

        User user = principalService.getPrincipal();

        mealData.setUser(user);

        if(mealData.getIngredients() == null) {
            mealData.setIngredients(new ArrayList<>());
        }
        mealData.getIngredients()
                .forEach(ingredient -> {
                    ingredient.setMeal(mealData);
                    validationService.validateThrowExceptionIfInvalid(ingredient, ingredientValidator);
                });

        validationService.validateThrowExceptionIfInvalid(mealData, mealValidator);

        mealRepository.saveAndFlush(mealData);

        entityManager.clear();

        return mealRepository.findById(mealData.getId()).get();
    }

    @Override
    public Meal getMealById(String id) {

        User user = principalService.getPrincipal();

        Meal meal = mealRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Блюдо с id [%s] не существует", id))
        );

        if(!user.getId().equals(meal.getUserId())) {
            throw new AccessDeniedException(String.format("Вы не имеете доступа к блюду с id [%s].", id));
        }

        return meal;
    }

    @Override
    public void deleteMealById(String id) {

        User user = principalService.getPrincipal();

        if(id == null || !mealRepository.existsByIdAndUser(id, user)) {
            throw new EntityNotFoundException(String.format("Блюдо с id [%s] не существует", id));
        }

        mealRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Meal> getCurrentUserMeals() {

        User user = principalService.getPrincipal();

        return mealRepository.findAllByUser(user);
    }

    @Override
    public boolean belongsTo(@NonNull String mealId, @NonNull String userId) {

        Meal meal = getMealById(mealId);

        return meal.getUserId().equals(userId);
    }
}
