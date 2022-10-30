package com.application.opencrm.inventory.service;

import com.application.opencrm.common.exception.ResourceNotFoundException;
import com.application.opencrm.common.exception.ResourceNotUniqueException;
import com.application.opencrm.inventory.dto.CategoryChildDto;
import com.application.opencrm.inventory.dto.CategoryCreationRequestDto;
import com.application.opencrm.inventory.dto.CategoryParentDto;
import com.application.opencrm.inventory.dto.CategoryUpdateRequestDto;
import com.application.opencrm.inventory.mapper.CategoryMapper;
import com.application.opencrm.inventory.model.Category;
import com.application.opencrm.inventory.repository.CategoryRepository;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service handling operations on {@link Category} objects.
 * <p>
 * This class serves as a layer between {@link CategoryRepository} and controllers. Entities ({@code Category}
 * objects) should generally not leave the service layer. Public or protected methods that return entities are
 * intended for communication with other services.
 */
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    private final Slugify slugify;

    /**
     * Retrieves all {@link Category} entities from repository.
     *
     * @return the list of dtos representing all found {@code Category} entities
     */
    @Transactional(readOnly = true)
    public List<CategoryChildDto> getAllCategories() {
        return getAllCategoriesFromRepository()
                   .stream()
                   .map(mapper::categoryToCategoryChildDto)
                   .collect(Collectors.toList());
    }

    /**
     * Retrieves a {@link Category} entity with specified {@code id} from repository.
     *
     * @param id the id of the searched {@code Category}
     * @return the dto representing the found {@code Category} entity
     */
    @Transactional(readOnly = true)
    public CategoryParentDto getCategoryWithInventories(Long id) {
        return mapper.categoryToCategoryParentDto(getCategoryFromRepository(id));
    }

    /**
     * Retrieves a {@link Category} entity with specified {@code slug} from repository.
     *
     * @param slug the slug of the searched {@code Category}
     * @return the dto representing the found {@code Category} entity
     */
    @Transactional(readOnly = true)
    public CategoryParentDto getCategoryWithInventories(String slug) {
        return mapper.categoryToCategoryParentDto(getCategoryFromRepository(slug));
    }

    /**
     * Creates a new {@link Category} and stores it in database. The data of the new category should be passed as data
     * of a {@code CategoryRequestDto} in the request body.
     *
     * @param request the object containing data of {@code Category} entity to be created
     * @return the dto representing the created {@code Category} entity
     */
    @Transactional
    public CategoryChildDto saveCategory(CategoryCreationRequestDto request) {
        Category category = mapper.categoryRequestDtoToCategory(request);
        updateSlug(category);
        Category savedCategory = saveCategoryToRepository(category);
        return mapper.categoryToCategoryChildDto(savedCategory);
    }

    /**
     * Updates an existing {@link Category} entity in database. Slug gets updated automatically.
     * <p>
     * The fields that need to be changed have to be set in the {@code CategoryRequestDto} from the request body.
     * If a field of the request object is set to {@code null}, the corresponding field in the target entity is not
     * affected - it is not set to {@code null}. It is not possible to set a field of {@code Category} entity to
     * {@code null} through this method.
     *
     * @param id      the {@code id} of the client entity to be updated
     * @param request the object containing new data used for the update
     * @return the dto representing the updated {@code Category} entity after the update
     */
    @Transactional
    public CategoryChildDto updateCategory(Long id, CategoryUpdateRequestDto request) {
        Category categoryToUpdate = getCategoryFromRepository(id);
        mapper.updateCategory(categoryToUpdate, request);
        updateSlug(categoryToUpdate);
        Category updatedCategory = saveCategoryToRepository(categoryToUpdate);
        return mapper.categoryToCategoryChildDto(updatedCategory);
    }

    /**
     * Delete a {@link Category} entity with specified {@code id} in database.
     *
     * @param id the {@code id} of the {@code Category} entity which is to be deleted
     */
    @Transactional
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    /**
     * Retrieves all {@link Category} entities from repository. The returned entity is not intended to be used externally outside the service layer.
     *
     * @return the list of dtos representing all found {@code Category} entities
     */
    protected List<Category> getAllCategoriesFromRepository() {
        return repository.findAll();
    }

    /**
     * Retrieves a {@link Category} entity with specified {@code id} from repository. The returned entity is not
     * intended to be used externally outside the service layer.
     *
     * @param id the {@code id} of the {@code Category} entity which is to be retrieved
     * @return the list of dtos representing all found {@code Category} entities
     */
    protected Category getCategoryFromRepository(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new ResourceNotFoundException("Category not found in the database."));
    }

    /**
     * Retrieves a {@link Category} entity with specified {@code slug} from repository. The returned entity is not
     * intended to be used externally outside the service layer.
     *
     * @param slug the {@code slug} of the {@code Category} entity which is to be retrieved
     * @return the list of dtos representing all found {@code Category} entities
     */
    protected Category getCategoryFromRepository(String slug) {
        return repository.findBySlug(slug)
                         .orElseThrow(() -> new ResourceNotFoundException("Category not found in the database."));
    }

    private Category saveCategoryToRepository(Category category) {
        try {
            return repository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotUniqueException("Save operation was not performed. Category with generated slug / " +
                                                     "name already exists. Slug / name has to be unique.");
        }
    }

    private void updateSlug(Category category) {
        category.setSlug(slugify.slugify(category.getName()));
    }


}
