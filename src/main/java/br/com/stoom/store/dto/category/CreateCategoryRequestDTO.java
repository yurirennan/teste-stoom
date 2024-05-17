package br.com.stoom.store.dto.category;

import br.com.stoom.store.model.Category;

import java.util.Objects;

public class CreateCategoryRequestDTO {

    private String name;
    private String description;

    public CreateCategoryRequestDTO() {
    }

    public CreateCategoryRequestDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCategoryRequestDTO that = (CreateCategoryRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    public Category toCategory() {
        final Category category = new Category();

        category.setName(this.getName());
        category.setDescription(this.getDescription());

        return category;
    }

}
