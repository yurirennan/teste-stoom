package br.com.stoom.store.repository;

import br.com.stoom.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product p SET p.active = ?2 WHERE p.id = ?1")
    void updateProductStatusById(final Long productId, final boolean status);

    Optional<Product> findProductByIdAndActiveTrue(final Long productId);

}