package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.dto.CustomPageImpl;
import br.com.stoom.store.dto.category.CreateCategoryRequestDTO;
import br.com.stoom.store.dto.category.ReadCategoryResponseDTO;
import br.com.stoom.store.dto.category.UpdateCategoryStatusDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryBO {

    void createCategory(final CreateCategoryRequestDTO categoryRequestDTO);

    ReadCategoryResponseDTO listCategory(final Long categoryId);

    CustomPageImpl<ReadCategoryResponseDTO> listAllCategory(Pageable pageable);

    void updateCategoryStatus(final Long categoryId, final UpdateCategoryStatusDTO updateCategoryStatusDTO);

}
