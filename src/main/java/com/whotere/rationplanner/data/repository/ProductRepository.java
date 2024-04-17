package com.whotere.rationplanner.data.repository;

import com.whotere.rationplanner.data.model.Product;
import com.whotere.rationplanner.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> getAllByUser(User user);

    boolean existsByIdAndUser(String id, User user);
}