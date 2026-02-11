package com.alex.emarket_api.controller;

import com.alex.emarket_api.dto.ProductDTO;
import com.alex.emarket_api.entity.Product;
import com.alex.emarket_api.exception.ModelNotFoundException;
import com.alex.emarket_api.service.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductService service;

    @MockitoBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    Product PRODUCT_1 = new Product(1L, "SSD NVMe Kingston 1TB", "Storage");
    Product PRODUCT_2 = new Product(2L, "Laptop HP Pavilion 15", "Electronics");
    Product PRODUCT_3 = new Product(3L, "Mouse inalámbrico Razer", "Accessories");

    ProductDTO PRODUCTDTO_1 = new ProductDTO(1L, "SSD NVMe Kingston 1TB", "Storage");
    ProductDTO PRODUCTDTO_2 = new ProductDTO(2L, "Laptop HP Pavilion 15", "Electronics");
    ProductDTO PRODUCTDTO_3 = new ProductDTO(3L, "Mouse inalámbrico Razer", "Accessories");
    //ProductDTO PRODUCTDTOE_3 = new ProductDTO(3L, "Mouse inalámbrico Razer", "Accessories");

    @Test
    public void getProductsTest() throws Exception{

        Mockito.when(service.getAllProducts())
                        .thenReturn(Arrays.asList(PRODUCT_1,PRODUCT_2, PRODUCT_3));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)));
    }

    @Test
    public void getProductByIdTest() throws Exception{
        final Long ID = 1L;

        Mockito.when(service.getProductById(ID)).thenReturn(PRODUCT_1);
        Mockito.when(modelMapper.map(PRODUCT_1, ProductDTO.class)).thenReturn(PRODUCTDTO_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/products/{id}",ID)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameProduct", is("SSD NVMe Kingston 1TB")));
    }

    @Test
    public void saveProductTest() throws Exception{

        Mockito.when(service.saveProduct(Mockito.any(Product.class)))
                .thenReturn(PRODUCT_3);

        Mockito.when(modelMapper.map(Mockito.any(Product.class), Mockito.eq(ProductDTO.class))) //Mockito.eq(ProductDTO.class) means exactly the class ProductDTO
                .thenReturn(PRODUCTDTO_3);

        Mockito.when(modelMapper.map(Mockito.any(ProductDTO.class), Mockito.eq(Product.class)))
                .thenReturn(PRODUCT_3);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCTDTO_3))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.typeProduct", is("Accessories"))
                );
    }

    @Test
    public void updateTest() throws Exception {
        final Long ID = 2L;
        Mockito.when(service.updateProduct(ID,PRODUCT_2)).thenReturn(PRODUCT_2);

        Mockito.when(modelMapper.map(Mockito.any(Product.class), Mockito.eq(ProductDTO.class)))
                .thenReturn(PRODUCTDTO_2);

        Mockito.when(modelMapper.map(Mockito.any(ProductDTO.class), Mockito.eq(Product.class)))
                .thenReturn(PRODUCT_2);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/products/{id}",ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCTDTO_2))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void updateErrorTest() throws Exception {
        final Long ID = 99L;

        Mockito.when(service.updateProduct(Mockito.eq(ID), Mockito.any(Product.class)))
                .thenThrow(new ModelNotFoundException("ID is not valid " + ID));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/products/", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(PRODUCTDTO_2))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteTest() throws Exception {
        final Long ID = 1L;
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/products/{id}",ID)
        ).andExpect(status().isNoContent());
    }

    @Test
    public void deleteErrorTest() throws Exception {
        Mockito.doThrow(new ModelNotFoundException("ID is no valid")).when(service).deleteProduct(1L);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/products/1")
        ).andExpect(status().isNotFound());
    }
}
