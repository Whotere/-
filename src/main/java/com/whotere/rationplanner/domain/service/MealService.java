package com.whotere.rationplanner.domain.service;

import com.whotere.rationplanner.data.model.Meal;

import java.util.List;

public interface MealService {

    Meal createMeal(Meal mealData);

    Meal getMealById(String id);

    void deleteMealById(String id);

    List<Meal> getCurrentUserMeals();

    boolean belongsTo(String mealId, String userId);
}
