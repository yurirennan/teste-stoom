package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.dto.CustomPageImpl;
import br.com.stoom.store.dto.product.CreateProductRequestDTO;
import br.com.stoom.store.dto.product.ReadProductResponseDTO;
import br.com.stoom.store.dto.product.UpdateProductStatusDTO;
import br.com.stoom.store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductBO {

    CustomPageImpl<ReadProductResponseDTO> findAll(final Pageable pageable);

    void saveProduct(final CreateProductRequestDTO createProductRequestDTO);

    ReadProductResponseDTO listProduct(final Long productId);

    void updateProductStatus(final Long productId, final UpdateProductStatusDTO updateProductStatusDTO);

    void deleteProduct(final Long productId);

    CustomPageImpl<ReadProductResponseDTO> listAllProductsByBrand(final Long brandId, final Pageable pageable);

    CustomPageImpl<ReadProductResponseDTO> listAllProductsByCategory(final Long categoryId, final Pageable pageable);
}
