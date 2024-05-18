package br.com.stoom.store.repository;

import br.com.stoom.store.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Modifying
    @Query("UPDATE Category c SET c.active = ?2 WHERE c.id = ?1")
    void updateCategoryStatusById(final Long categoryId, final boolean status);

    Optional<Category> findCategoryByIdAndActiveTrue(final Long categoryId);

}
