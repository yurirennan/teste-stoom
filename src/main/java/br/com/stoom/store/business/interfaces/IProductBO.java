package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.dto.product.CreateProductRequestDTO;
import br.com.stoom.store.dto.product.ReadProductResponseDTO;
import br.com.stoom.store.dto.product.UpdateProductStatusDTO;
import br.com.stoom.store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductBO {

    Page<ReadProductResponseDTO> findAll(final Pageable pageable);

    void saveProduct(final CreateProductRequestDTO createProductRequestDTO);

    ReadProductResponseDTO listProduct(final Long productId);

    void updateProductStatus(final Long productId, final UpdateProductStatusDTO updateProductStatusDTO);

    void deleteProduct(final Long productId);

    Page<ReadProductResponseDTO> listAllProductsByBrand(final Long brandId, final Pageable pageable);

    Page<ReadProductResponseDTO> listAllProductsByCategory(final Long categoryId, final Pageable pageable);
}
