package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.dto.CustomPageImpl;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    @CacheEvict(value = "brands", allEntries = true)
    public void createBrand(final CreateBrandRequestDTO brandRequestDTO) {
        final Brand brand = brandRequestDTO.toBrand();
        this.brandRepository.save(brand);
    }

    @Override
    @Cacheable(value = "brand", key = "#brandId")
    public ReadBrandResponseDTO listBrand(final Long brandId) {
        final Optional<Brand> brandOptional = this.brandRepository.findBrandByIdAndActiveTrue(brandId);

        if (!brandOptional.isPresent()) {
            throw new BrandNotFoundException("Brand Not Found!");
        }

        final Brand brand = brandOptional.get();

        return ReadBrandResponseDTO.fromBrand(brand);
    }

    @Override
    @Cacheable("brands")
    public CustomPageImpl<ReadBrandResponseDTO> listAllBrands(Pageable pageable) {
        final Page<Brand> brandsPage = this.brandRepository.findAllByActiveTrue(pageable);

        final List<ReadBrandResponseDTO> readBrandResponseDTOList = brandsPage
                .getContent()
                .stream()
                .map(ReadBrandResponseDTO::fromBrand)
                .collect(Collectors.toList());

        return new CustomPageImpl<>(readBrandResponseDTOList, brandsPage.getPageable(), brandsPage.getTotalElements());
    }

    @Override
    @Transactional
    @CacheEvict(value = "brand", key = "#brandId")
    public void updateBrandStatus(final Long brandId, final UpdateBrandStatusDTO updateBrandStatusDTO) {
        this.brandRepository.updateBrandStatusById(brandId, updateBrandStatusDTO.getStatus());
    }

}
