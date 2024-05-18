package br.com.stoom.store.dto.category;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UpdateCategoryStatusDTO {

    @NotNull(message = "status is mandatory")
    private boolean status;

    public UpdateCategoryStatusDTO() {
    }

    public UpdateCategoryStatusDTO(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCategoryStatusDTO that = (UpdateCategoryStatusDTO) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

}
