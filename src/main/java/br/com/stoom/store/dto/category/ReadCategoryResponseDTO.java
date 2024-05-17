package br.com.stoom.store.dto.category;


import br.com.stoom.store.model.Category;

import java.util.Objects;

public class ReadCategoryResponseDTO {

    private Long id;
    private String name;
    private String description;
    private boolean active ;

    public ReadCategoryResponseDTO() {
    }

    public ReadCategoryResponseDTO(Long id, String name, String description, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadCategoryResponseDTO that = (ReadCategoryResponseDTO) o;
        return active == that.active && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, active);
    }

    public static ReadCategoryResponseDTO fromCategory(final Category category) {
        final ReadCategoryResponseDTO readCategoryResponseDTO = new ReadCategoryResponseDTO();

        readCategoryResponseDTO.setId(category.getId());
        readCategoryResponseDTO.setName(category.getName());
        readCategoryResponseDTO.setDescription(category.getDescription());
        readCategoryResponseDTO.setActive(category.isActive());

        return readCategoryResponseDTO;
    }

}
