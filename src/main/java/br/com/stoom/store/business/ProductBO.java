package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.dto.product.CreateProductRequestDTO;
import br.com.stoom.store.dto.product.ReadProductResponseDTO;
import br.com.stoom.store.dto.product.UpdateProductStatusDTO;
import br.com.stoom.store.exceptions.brand.BrandNotExistsException;
import br.com.stoom.store.exceptions.brand.BrandNotFoundException;
import br.com.stoom.store.exceptions.category.CategoryNotExistsException;
import br.com.stoom.store.exceptions.category.CategoryNotFoundException;
import br.com.stoom.store.exceptions.product.ProductNotFoundException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.repository.CategoryRepository;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductBO implements IProductBO {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductBO(final ProductRepository productRepository,
                     final BrandRepository brandRepository,
                     final CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<ReadProductResponseDTO> findAll(final Pageable pageable){
        final Page<Product> productPage = this.productRepository.findAllByActiveTrue(pageable);

        final List<ReadProductResponseDTO> readProductResponseDTOS = productPage
                .getContent()
                .stream()
                .map(ReadProductResponseDTO::fromProduct)
                .collect(Collectors.toList());

        return new PageImpl<>(readProductResponseDTOS, productPage.getPageable(), productPage.getTotalElements());
    }

    @Override
    public void saveProduct(final CreateProductRequestDTO createProductRequestDTO) {

        final Optional<Brand> brandOptional =
                this.brandRepository.findBrandByIdAndActiveTrue(createProductRequestDTO.getBrandId());

        if (!brandOptional.isPresent()) {
            throw new BrandNotExistsException("Brand Not Exists!");
        }

        final Optional<Category> categoryOptional =
                this.categoryRepository.findCategoryByIdAndActiveTrue(createProductRequestDTO.getCategoryId());

        if (!categoryOptional.isPresent()) {
            throw new CategoryNotExistsException("Category Not Exists!");
        }

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
        final Optional<Product> productOptional =
                this.productRepository.findById(productId);

        if (!productOptional.isPresent()) {
            throw new ProductNotFoundException("Product Not Found!");
        }

        this.productRepository.updateProductStatusById(productId, updateProductStatusDTO.getStatus());
    }

    @Override
    public void deleteProduct(final Long productId) {
        final Optional<Product> productOptional =
                this.productRepository.findProductByIdAndActiveTrue(productId);

        if (!productOptional.isPresent()) {
            throw new ProductNotFoundException("Product Not Found!");
        }

        this.productRepository.deleteById(productId);
    }

    @Override
    public Page<ReadProductResponseDTO> listAllProductsByBrand(final Long brandId, final Pageable pageable) {
        final Optional<Brand> brandOptional = this.brandRepository.findBrandByIdAndActiveTrue(brandId);

        if (!brandOptional.isPresent()) {
            throw new BrandNotFoundException("Brand Not Found!");
        }
        final Brand brand = brandOptional.get();

        final Page<Product> productsPage = this.productRepository.findAllByBrandIdAndActiveTrue(brand.getId(), pageable);

        final List<ReadProductResponseDTO> readProductResponseDTOS = productsPage
                .getContent()
                .stream()
                .map(ReadProductResponseDTO::fromProduct)
                .collect(Collectors.toList());

        return new PageImpl<>(readProductResponseDTOS, productsPage.getPageable(), productsPage.getTotalElements());
    }

    @Override
    public Page<ReadProductResponseDTO> listAllProductsByCategory(final Long categoryId, final Pageable pageable) {
        final Optional<Category> categoryOptional = this.categoryRepository.findCategoryByIdAndActiveTrue(categoryId);

        if (!categoryOptional.isPresent()) {
            throw new CategoryNotFoundException("Category Not Found!");
        }

        final Category category = categoryOptional.get();

        final Page<Product> productsPage = this.productRepository.findAllByCategoryIdAndActiveTrue(category.getId(), pageable);

        final List<ReadProductResponseDTO> readProductResponseDTOS = productsPage
                .getContent()
                .stream()
                .map(ReadProductResponseDTO::fromProduct)
                .collect(Collectors.toList());

        return new PageImpl<>(readProductResponseDTOS, productsPage.getPageable(), productsPage.getTotalElements());
    }

}
