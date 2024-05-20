package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.dto.CustomPageImpl;
import br.com.stoom.store.dto.brand.CreateBrandRequestDTO;
import br.com.stoom.store.dto.brand.ReadBrandResponseDTO;
import br.com.stoom.store.dto.brand.UpdateBrandStatusDTO;
import org.springframework.data.domain.Pageable;

public interface IBrandBO {

    void createBrand(final CreateBrandRequestDTO brandRequestDTO);


    ReadBrandResponseDTO listBrand(final Long brandId);

    CustomPageImpl<ReadBrandResponseDTO> listAllBrands(Pageable pageable);

    void updateBrandStatus(final Long brandId, final UpdateBrandStatusDTO updateBrandStatusDTO);

}
