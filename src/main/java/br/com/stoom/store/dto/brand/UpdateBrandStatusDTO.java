package br.com.stoom.store.dto.brand;

import java.util.Objects;

public class UpdateBrandStatusDTO {

    private boolean status;

    public UpdateBrandStatusDTO() {
    }

    public UpdateBrandStatusDTO(boolean status) {
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
        UpdateBrandStatusDTO that = (UpdateBrandStatusDTO) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

}
