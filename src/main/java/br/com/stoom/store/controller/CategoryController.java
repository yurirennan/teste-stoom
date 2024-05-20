package br.com.stoom.store.controller;

import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.dto.CustomPageImpl;
import br.com.stoom.store.dto.category.CreateCategoryRequestDTO;
import br.com.stoom.store.dto.category.ReadCategoryResponseDTO;
import br.com.stoom.store.dto.category.UpdateCategoryStatusDTO;
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
@RequestMapping("/api/categories")
public class CategoryController {

    private final ICategoryBO categoryBO;
    private final IProductBO productBO;

    @Autowired
    public CategoryController(final ICategoryBO categoryBO, final IProductBO productBO) {
        this.categoryBO = categoryBO;
        this.productBO = productBO;
    }

    @PostMapping()
    public ResponseEntity<Void> saveCategory(@RequestBody @Valid CreateCategoryRequestDTO categoryRequestDTO) {
        this.categoryBO.createCategory(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadCategoryResponseDTO> listCategory(@PathVariable("id") final Long categoryId) {
        final ReadCategoryResponseDTO readCategoryResponseDTO = this.categoryBO.listCategory(categoryId);

        return ResponseEntity.status(HttpStatus.OK).body(readCategoryResponseDTO);
    }

    @GetMapping
    public ResponseEntity<CustomPageImpl<ReadCategoryResponseDTO>> listAllCategory(Pageable pageable) {
        final CustomPageImpl<ReadCategoryResponseDTO> readCategoryResponseDTOS = this.categoryBO.listAllCategory(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(readCategoryResponseDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategoryStatus(@PathVariable("id") final Long categoryId,
                                                     @RequestBody @Valid final UpdateCategoryStatusDTO updateCategoryStatusDTO) {
        this.categoryBO.updateCategoryStatus(categoryId, updateCategoryStatusDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<CustomPageImpl<ReadProductResponseDTO>> listAllProductsByCategory(@PathVariable("id") final Long categoryId, final Pageable pageable) {
        final CustomPageImpl<ReadProductResponseDTO> readProductResponseDTOS = this.productBO.listAllProductsByCategory(categoryId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(readProductResponseDTOS);
    }

}
