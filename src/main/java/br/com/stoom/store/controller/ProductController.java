package br.com.stoom.store.controller;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.dto.product.CreateProductRequestDTO;
import br.com.stoom.store.dto.product.ReadProductResponseDTO;
import br.com.stoom.store.dto.product.UpdateProductStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private IProductBO productService;

    @Autowired
    public ProductController(final IProductBO productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ReadProductResponseDTO>> findAll() {
        final List<ReadProductResponseDTO> readProductResponseDTOS = productService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(readProductResponseDTOS);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody final CreateProductRequestDTO createProductRequestDTO) {
        this.productService.saveProduct(createProductRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadProductResponseDTO> listProduct(@PathVariable("id") final Long productId){
        final ReadProductResponseDTO readProductResponseDTO = this.productService.listProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).body(readProductResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProductStatus(@PathVariable("id") final Long productId,
                                                    @RequestBody final UpdateProductStatusDTO updateProductStatusDTO) {
        this.productService.updateProductStatus(productId, updateProductStatusDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") final Long productId){
        this.productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
