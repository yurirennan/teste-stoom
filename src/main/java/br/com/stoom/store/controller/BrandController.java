package br.com.stoom.store.controller;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.dto.brand.CreateBrandRequestDTO;
import br.com.stoom.store.dto.brand.ReadBrandResponseDTO;
import br.com.stoom.store.dto.brand.UpdateBrandStatusDTO;
import br.com.stoom.store.dto.product.ReadProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Void> saveBrand(@RequestBody @Valid CreateBrandRequestDTO createBrandRequestDTO) {
        this.brandBO.createBrand(createBrandRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadBrandResponseDTO> listBrand(@PathVariable("id") final Long brandId) {
        final ReadBrandResponseDTO readBrandResponseDTO = this.brandBO.listBrand(brandId);

        return ResponseEntity.status(HttpStatus.OK).body(readBrandResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ReadBrandResponseDTO>> listAllBrands(final Pageable pageable) {
        final Page<ReadBrandResponseDTO> readBrandResponseDTOList = this.brandBO.listAllBrands(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(readBrandResponseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBrandStatus(@PathVariable("id") final Long brandId,
                                                  @RequestBody @Valid final UpdateBrandStatusDTO updateBrandStatusDTO) {
        this.brandBO.updateBrandStatus(brandId, updateBrandStatusDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Page<ReadProductResponseDTO>> listAllProductsByBrand(@PathVariable("id") final Long brandId,
                                                                               final Pageable pageable) {
        final Page<ReadProductResponseDTO> readProductResponseDTOS =
                this.productBO.listAllProductsByBrand(brandId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(readProductResponseDTOS);
    }
}
