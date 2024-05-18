package br.com.stoom.store.repository;

import br.com.stoom.store.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Modifying
    @Query("UPDATE Brand b SET b.active = ?2 WHERE b.id = ?1")
    void updateBrandStatusById(final Long brandId, final boolean status);

}
