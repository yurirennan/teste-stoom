package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.dto.category.CreateCategoryRequestDTO;
import br.com.stoom.store.dto.category.ReadCategoryResponseDTO;
import br.com.stoom.store.dto.category.UpdateCategoryStatusDTO;
import br.com.stoom.store.exceptions.category.CategoryNotFoundException;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryBO implements ICategoryBO {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryBO(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createCategory(final CreateCategoryRequestDTO categoryRequestDTO) {
        final Category category = categoryRequestDTO.toCategory();
        this.categoryRepository.save(category);
    }

    @Override
    public ReadCategoryResponseDTO listCategory(final Long categoryId) {
        final Optional<Category> categoryOptional = this.categoryRepository.findCategoryByIdAndActiveTrue(categoryId);

        if (!categoryOptional.isPresent()) {
            throw new CategoryNotFoundException("Categoria não existe");
        }

        final Category category = categoryOptional.get();

        return ReadCategoryResponseDTO.fromCategory(category);
    }

    @Override
    public List<ReadCategoryResponseDTO> listAllCategory() {
        final List<Category> allCategories = this.categoryRepository.findAll();

        final List<ReadCategoryResponseDTO> categoriesDTO = allCategories
                .stream()
                .map(ReadCategoryResponseDTO::fromCategory).collect(Collectors.toList());

        return categoriesDTO;
    }

    @Override
    @Transactional
    public void updateCategoryStatus(final Long categoryId, final UpdateCategoryStatusDTO updateCategoryStatusDTO) {
        this.categoryRepository.updateCategoryStatusById(categoryId, updateCategoryStatusDTO.getStatus());
    }

}
