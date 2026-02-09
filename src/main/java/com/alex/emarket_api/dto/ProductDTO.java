package com.alex.emarket_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ProductDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idProduct;
    private String nameProduct;
    private String typeProduct;

    public ProductDTO() {
    }

    public ProductDTO(Long idProduct, String nameProduct, String typeProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.typeProduct = typeProduct;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    @Override
    public String
    toString() {
        return "ProductDTO{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", typeProduct='" + typeProduct + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(idProduct, that.idProduct) && Objects.equals(nameProduct, that.nameProduct) && Objects.equals(typeProduct, that.typeProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, nameProduct, typeProduct);
    }
}
