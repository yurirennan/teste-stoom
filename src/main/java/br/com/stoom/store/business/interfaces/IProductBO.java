package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.dto.product.CreateProductRequestDTO;
import br.com.stoom.store.dto.product.ReadProductResponseDTO;
import br.com.stoom.store.dto.product.UpdateProductStatusDTO;
import br.com.stoom.store.model.Product;

import java.util.List;

public interface IProductBO {

    List<ReadProductResponseDTO> findAll();

    void saveProduct(final CreateProductRequestDTO createProductRequestDTO);

    ReadProductResponseDTO listProduct(final Long productId);

    void updateProductStatus(final Long productId, final UpdateProductStatusDTO updateProductStatusDTO);

    void deleteProduct(final Long productId);

    List<ReadProductResponseDTO> listAllProductsByBrand(final Long brandId);
}
