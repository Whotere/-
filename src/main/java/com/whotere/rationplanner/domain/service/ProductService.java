package com.whotere.rationplanner.domain.service;

import com.whotere.rationplanner.data.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product productData);

    Product getProductById(String id);

    List<Product> getCurrentUserProducts();

    void deleteProductById(String id);
}
