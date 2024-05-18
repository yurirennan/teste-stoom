package br.com.stoom.store.repository;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Modifying
    @Query("UPDATE Brand b SET b.active = ?2 WHERE b.id = ?1")
    void updateBrandStatusById(final Long brandId, final boolean status);

    Optional<Brand> findBrandByIdAndActiveTrue(final Long brandId);

    Page<Brand> findAllByActiveTrue(Pageable pageable);

}
