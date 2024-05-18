package br.com.stoom.store.dto.product;


import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class ReadProductResponseDTO {

    private Long id;
    private String sku;
    private String name;
    private BigDecimal price;
    private boolean active ;
    @JsonProperty("category_id")
    private Long categoryId;
    @JsonProperty("brand_id")
    private Long brandId;

    public ReadProductResponseDTO() {
    }

    public ReadProductResponseDTO(Long id, String sku, String name, BigDecimal price, boolean active, Long categoryId, Long brandId) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.active = active;
        this.categoryId = categoryId;
        this.brandId = brandId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        ReadProductResponseDTO that = (ReadProductResponseDTO) o;
        return active == that.active && Objects.equals(id, that.id) && Objects.equals(sku, that.sku) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(categoryId, that.categoryId) && Objects.equals(brandId, that.brandId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku, name, price, active, categoryId, brandId);
    }

    public static ReadProductResponseDTO fromProduct(final Product product) {
        final ReadProductResponseDTO readProductResponseDTO = new ReadProductResponseDTO();

        readProductResponseDTO.setId(product.getId());
        readProductResponseDTO.setSku(product.getSku());
        readProductResponseDTO.setName(product.getName());
        readProductResponseDTO.setPrice(product.getPrice());
        readProductResponseDTO.setActive(product.isActive());
        readProductResponseDTO.setBrandId(product.getBrand().getId());
        readProductResponseDTO.setCategoryId(product.getCategory().getId());

        return readProductResponseDTO;
    }

}
