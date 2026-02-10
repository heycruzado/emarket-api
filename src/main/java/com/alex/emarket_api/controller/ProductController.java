package com.alex.emarket_api.controller;


import com.alex.emarket_api.dto.ProductDTO;
import com.alex.emarket_api.entity.Product;
import com.alex.emarket_api.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    private final ModelMapper mapper;

    public ProductController(ProductService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() throws Exception {
        List<ProductDTO> list = service.getAllProducts()
                .stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) throws Exception {
        ProductDTO productDTO = convertToDTO(service.getProductById(id));
        return ResponseEntity.ok().body(productDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) throws Exception {
        Product product = service.saveProduct(convertToEntity(productDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) throws Exception {
        Product product = service.updateProduct(id, convertToEntity(productDTO));
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) throws Exception {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<ProductDTO>> findPage(Pageable pageable) throws Exception{
        Page<ProductDTO> page = service.findPage(pageable).map(this::convertToDTO);

        return ResponseEntity.ok().body(page);
    }

    private ProductDTO convertToDTO(Product product){
        return mapper.map(product, ProductDTO.class);
    }

    private Product convertToEntity(ProductDTO dto){
        return mapper.map(dto, Product.class);
    }

}
