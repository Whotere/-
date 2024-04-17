package com.whotere.rationplanner.data.repository;

import com.whotere.rationplanner.data.model.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, String> {}
