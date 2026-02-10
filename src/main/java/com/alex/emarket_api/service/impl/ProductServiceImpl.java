package com.alex.emarket_api.service.impl;

import com.alex.emarket_api.entity.Product;
import com.alex.emarket_api.exception.ModelNotFoundException;
import com.alex.emarket_api.repository.ProductRepository;
import com.alex.emarket_api.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts()  throws Exception{
        return repository.findAll();
    }

    @Override
    public Product getProductById(Long id) throws Exception {
        List<Product> findAll = repository.findAll();
        return findAll.stream().filter(product -> product.getIdProduct().equals(id))
                .findFirst()
                .orElseThrow(() -> new ModelNotFoundException("Product not found"));

    }

    @Override
    public Product saveProduct(Product product) throws Exception {
        Optional<Product> existing = repository.findByName(product.getName());
        if (existing.isPresent()) {
            throw new RuntimeException("Product already exists in the database");
        }
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws Exception {
        Product productSelected = repository.findById(id).orElseThrow(() -> new ModelNotFoundException("Id not found" + id));
        productSelected.setName(product.getName());
        productSelected.setType(product.getType());
        return repository.save(productSelected);

    }

    @Override
    public void deleteProduct(Long id)  throws Exception {
        repository.findById(id).orElseThrow(() -> new ModelNotFoundException("Id not found"+id));
        repository.deleteById(id);
    }

    @Override
    public Page<Product> findPage(Pageable pageable) {
        return repository.findAll(pageable);
    }


}
