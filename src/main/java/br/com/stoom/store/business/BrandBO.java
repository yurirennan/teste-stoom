package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.dto.brand.CreateBrandRequestDTO;
import br.com.stoom.store.dto.brand.ReadBrandResponseDTO;
import br.com.stoom.store.dto.brand.UpdateBrandStatusDTO;
import br.com.stoom.store.dto.category.CreateCategoryRequestDTO;
import br.com.stoom.store.dto.category.ReadCategoryResponseDTO;
import br.com.stoom.store.dto.category.UpdateCategoryStatusDTO;
import br.com.stoom.store.exceptions.brand.BrandNotFoundException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandBO implements IBrandBO {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandBO(final BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @Override
    public void createBrand(final CreateBrandRequestDTO brandRequestDTO) {
        final Brand brand = brandRequestDTO.toBrand();
        this.brandRepository.save(brand);
    }

    @Override
    public ReadBrandResponseDTO listBrand(final Long brandId) {
        final Optional<Brand> brandOptional = this.brandRepository.findBrandByIdAndActiveTrue(brandId);

        if (!brandOptional.isPresent()) {
            throw new BrandNotFoundException("Brand Not Found!");
        }

        final Brand brand = brandOptional.get();

        return ReadBrandResponseDTO.fromBrand(brand);
    }

    @Override
    public List<ReadBrandResponseDTO> listAllBrands() {
        final List<Brand> brands = this.brandRepository.findAllByActiveTrue();

        return brands
                .stream()
                .map(ReadBrandResponseDTO::fromBrand)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateBrandStatus(final Long brandId, final UpdateBrandStatusDTO updateBrandStatusDTO) {
        this.brandRepository.updateBrandStatusById(brandId, updateBrandStatusDTO.getStatus());
    }

}
