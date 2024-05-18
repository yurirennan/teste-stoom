package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.dto.brand.CreateBrandRequestDTO;
import br.com.stoom.store.dto.brand.ReadBrandResponseDTO;
import br.com.stoom.store.dto.brand.UpdateBrandStatusDTO;
import br.com.stoom.store.dto.category.CreateCategoryRequestDTO;
import br.com.stoom.store.dto.category.ReadCategoryResponseDTO;
import br.com.stoom.store.dto.category.UpdateCategoryStatusDTO;

import java.util.List;

public interface IBrandBO {

    void createBrand(final CreateBrandRequestDTO brandRequestDTO);


    ReadBrandResponseDTO listBrand(final Long brandId);

    List<ReadBrandResponseDTO> listAllBrands();

    void updateBrandStatus(final Long brandId, final UpdateBrandStatusDTO updateBrandStatusDTO);

}
