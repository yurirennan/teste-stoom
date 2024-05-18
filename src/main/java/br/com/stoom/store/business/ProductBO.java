package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.dto.product.CreateProductRequestDTO;
import br.com.stoom.store.dto.product.ReadProductResponseDTO;
import br.com.stoom.store.dto.product.UpdateProductStatusDTO;
import br.com.stoom.store.exceptions.product.ProductNotFoundException;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductBO implements IProductBO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductBO(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ReadProductResponseDTO> findAll(){
        final List<Product> productList = productRepository.findAll();

        return productList.stream().map(ReadProductResponseDTO::fromProduct).collect(Collectors.toList());
    }

    @Override
    public void saveProduct(final CreateProductRequestDTO createProductRequestDTO) {
        final Product product = createProductRequestDTO.toProduct();
        this.productRepository.save(product);
    }

    @Override
    public ReadProductResponseDTO listProduct(final Long productId) {
        final Optional<Product> productOptional = this.productRepository.findProductByIdAndActiveTrue(productId);

        if (!productOptional.isPresent()) {
            throw new ProductNotFoundException("Product Not Found!");
        }

        final Product product = productOptional.get();

        return ReadProductResponseDTO.fromProduct(product);
    }

    @Override
    @Transactional
    public void updateProductStatus(final Long productId, final UpdateProductStatusDTO updateProductStatusDTO) {
        this.productRepository.updateProductStatusById(productId, updateProductStatusDTO.getStatus());
    }

    @Override
    public void deleteProduct(final Long productId) {
        this.productRepository.deleteById(productId);
    }

}
