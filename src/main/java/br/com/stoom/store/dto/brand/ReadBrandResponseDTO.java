package br.com.stoom.store.dto.brand;


import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;

import java.util.Objects;

public class ReadBrandResponseDTO {

    private Long id;
    private String name;
    private String description;
    private boolean active ;

    public ReadBrandResponseDTO() {
    }

    public ReadBrandResponseDTO(final Long id, final String name, final String description, final boolean active) {
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
        ReadBrandResponseDTO that = (ReadBrandResponseDTO) o;
        return active == that.active && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, active);
    }

    public static ReadBrandResponseDTO fromBrand(final Brand brand) {
        final ReadBrandResponseDTO readBrandResponseDTO = new ReadBrandResponseDTO();

        readBrandResponseDTO.setId(brand.getId());
        readBrandResponseDTO.setName(brand.getName());
        readBrandResponseDTO.setDescription(brand.getDescription());
        readBrandResponseDTO.setActive(brand.isActive());

        return readBrandResponseDTO;
    }

}
