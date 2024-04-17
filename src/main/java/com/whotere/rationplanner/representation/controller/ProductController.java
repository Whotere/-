package com.whotere.rationplanner.representation.controller;

import com.whotere.rationplanner.data.model.Product;
import com.whotere.rationplanner.domain.mapper.ProductMapper;
import com.whotere.rationplanner.domain.service.ProductService;
import com.whotere.rationplanner.representation.dto.ProductDto;
import com.whotere.rationplanner.representation.dto.form.ProductCreationForm;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @ApiOperation("Получение информации о продукте по его id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {

        Product product = productService.getProductById(id);
        ProductDto productDto = productMapper.toProductDto(product);

        return ResponseEntity.ok(productDto);
    }

    @ApiOperation("Получение списка продуктов текущего пользователя")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {

        List<Product> products = productService.getCurrentUserProducts();
        List<ProductDto> productDots = products
                .stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDots);
    }

    @ApiOperation("Создание продукта")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductCreationForm productCreationForm) {

        Product product = productService.createProduct(productMapper.toProduct(productCreationForm));
        ProductDto productDto = productMapper.toProductDto(product);

        return ResponseEntity.ok(productDto);
    }

    @ApiOperation("Удаление продукта по его id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id) {

        productService.deleteProductById(id);

        return ResponseEntity.noContent().build();
    }
}
