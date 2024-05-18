package br.com.stoom.store.controller;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.dto.brand.CreateBrandRequestDTO;
import br.com.stoom.store.dto.brand.ReadBrandResponseDTO;
import br.com.stoom.store.dto.brand.UpdateBrandStatusDTO;
import br.com.stoom.store.dto.category.CreateCategoryRequestDTO;
import br.com.stoom.store.dto.category.ReadCategoryResponseDTO;
import br.com.stoom.store.dto.category.UpdateCategoryStatusDTO;
import br.com.stoom.store.dto.product.ReadProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final IBrandBO brandBO;
    private final IProductBO productBO;

    @Autowired
    public BrandController(final IBrandBO brandBO, final IProductBO productBO) {
        this.brandBO = brandBO;
        this.productBO = productBO;
    }

    @PostMapping()
    public ResponseEntity<Void> saveBrand(@RequestBody CreateBrandRequestDTO createBrandRequestDTO) {
        this.brandBO.createBrand(createBrandRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadBrandResponseDTO> listBrand(@PathVariable("id") final Long brandId) {
        final ReadBrandResponseDTO readBrandResponseDTO = this.brandBO.listBrand(brandId);

        return ResponseEntity.status(HttpStatus.OK).body(readBrandResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReadBrandResponseDTO>> listAllBrands() {
        final List<ReadBrandResponseDTO> readBrandResponseDTOList = this.brandBO.listAllBrands();

        return ResponseEntity.status(HttpStatus.OK).body(readBrandResponseDTOList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateBrandStatus(@PathVariable("id") final Long brandId,
                                                  @RequestBody final UpdateBrandStatusDTO updateBrandStatusDTO) {
        this.brandBO.updateBrandStatus(brandId, updateBrandStatusDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ReadProductResponseDTO>> listAllProductsByBrand(@PathVariable("id") final Long brandId) {
        final List<ReadProductResponseDTO> readProductResponseDTOS = this.productBO.listAllProductsByBrand(brandId);

        return ResponseEntity.status(HttpStatus.OK).body(readProductResponseDTOS);
    }
}
