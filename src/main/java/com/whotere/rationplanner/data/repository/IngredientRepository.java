package com.whotere.rationplanner.data.repository;

import com.whotere.rationplanner.data.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {}
