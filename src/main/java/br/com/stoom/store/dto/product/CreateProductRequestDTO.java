package br.com.stoom.store.dto.product;


import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class CreateProductRequestDTO {

    @NotBlank(message = "sku is mandatory")
    private String sku;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotNull(message = "price is mandatory")
    private BigDecimal price;
    @JsonProperty("category_id")
    @NotNull(message = "category_id is mandatory")
    private Long categoryId;
    @JsonProperty("brand_id")
    @NotNull(message = "brand_id is mandatory")
    private Long brandId;

    public CreateProductRequestDTO() {
    }

    public CreateProductRequestDTO(String sku, String name, BigDecimal price, Long categoryId, Long brandId) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.brandId = brandId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateProductRequestDTO that = (CreateProductRequestDTO) o;
        return Objects.equals(sku, that.sku) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(categoryId, that.categoryId) && Objects.equals(brandId, that.brandId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, price, categoryId, brandId);
    }

    public Product toProduct() {
        final Product product = new Product();

        product.setSku(this.getSku());
        product.setName(this.getName());
        product.setPrice(this.getPrice());
        product.setBrand(Brand.of(this.getBrandId()));
        product.setCategory(Category.of(this.getCategoryId()));

        return product;
    }

}
