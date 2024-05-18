package br.com.stoom.store.dto.product;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UpdateProductStatusDTO {

    @NotNull(message = "status is mandatory")
    private boolean status;

    public UpdateProductStatusDTO() {
    }

    public UpdateProductStatusDTO(boolean status) {
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
        UpdateProductStatusDTO that = (UpdateProductStatusDTO) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

}
