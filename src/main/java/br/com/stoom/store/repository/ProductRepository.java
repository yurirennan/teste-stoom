package br.com.stoom.store.repository;

import br.com.stoom.store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product p SET p.active = ?2 WHERE p.id = ?1")
    void updateProductStatusById(final Long productId, final boolean status);

    Optional<Product> findProductByIdAndActiveTrue(final Long productId);

    Page<Product> findAllByBrandIdAndActiveTrue(final Long productId, final Pageable pageable);

    Page<Product> findAllByCategoryIdAndActiveTrue(final Long categoryId, final Pageable pageable);

    Page<Product> findAllByActiveTrue(final Pageable pageable);

}