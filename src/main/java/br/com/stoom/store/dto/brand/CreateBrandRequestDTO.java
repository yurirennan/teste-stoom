package br.com.stoom.store.dto.brand;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;

import java.util.Objects;

public class CreateBrandRequestDTO {

    private String name;
    private String description;

    public CreateBrandRequestDTO() {
    }

    public CreateBrandRequestDTO(final String name,final String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateBrandRequestDTO that = (CreateBrandRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    public Brand toBrand() {
        final Brand brand = new Brand();

        brand.setName(this.getName());
        brand.setDescription(this.getDescription());

        return brand;
    }

}
