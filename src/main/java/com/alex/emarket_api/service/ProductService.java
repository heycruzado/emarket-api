package com.alex.emarket_api.service;

import com.alex.emarket_api.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts() throws Exception;
    Product getProductById(Long id) throws Exception;
    Product saveProduct(Product product) throws Exception;
    Product updateProduct(Long id, Product product) throws Exception;
    void deleteProduct(Long id) throws Exception;
    Page<Product> findPage(Pageable pageable) throws Exception;

}
