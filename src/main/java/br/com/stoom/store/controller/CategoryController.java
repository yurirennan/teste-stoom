package br.com.stoom.store.controller;

import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.dto.category.CreateCategoryRequestDTO;
import br.com.stoom.store.dto.category.ReadCategoryResponseDTO;
import br.com.stoom.store.dto.category.UpdateCategoryStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final ICategoryBO categoryBO;

    @Autowired
    public CategoryController(final ICategoryBO categoryBO) {
        this.categoryBO = categoryBO;
    }

    @PostMapping()
    public ResponseEntity<Void> saveCategory(@RequestBody CreateCategoryRequestDTO categoryRequestDTO) {
        this.categoryBO.createCategory(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadCategoryResponseDTO> listCategory(@PathVariable("id") final Long categoryId) {
        final ReadCategoryResponseDTO readCategoryResponseDTO = this.categoryBO.listCategory(categoryId);

        return ResponseEntity.status(HttpStatus.OK).body(readCategoryResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReadCategoryResponseDTO>> listAllCategory() {
        final List<ReadCategoryResponseDTO> readCategoryResponseDTOS = this.categoryBO.listAllCategory();

        return ResponseEntity.status(HttpStatus.OK).body(readCategoryResponseDTOS);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateCategoryStatus(@PathVariable("id") final Long categoryId,
                                                     @RequestBody final UpdateCategoryStatusDTO updateCategoryStatusDTO) {
        this.categoryBO.updateCategoryStatus(categoryId, updateCategoryStatusDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}