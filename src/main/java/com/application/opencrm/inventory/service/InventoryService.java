package com.application.opencrm.inventory.service;

import com.application.opencrm.common.exception.ResourceNotFoundException;
import com.application.opencrm.inventory.dto.InventoryCategoryRequestDto;
import com.application.opencrm.inventory.dto.InventoryCreationRequestDto;
import com.application.opencrm.inventory.dto.InventoryParentDto;
import com.application.opencrm.inventory.dto.InventoryUpdateRequestDto;
import com.application.opencrm.inventory.mapper.InventoryMapper;
import com.application.opencrm.inventory.model.Category;
import com.application.opencrm.inventory.model.Inventory;
import com.application.opencrm.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service handling operations on {@link Inventory} objects.
 * <p>
 * This class serves as a layer between {@link InventoryRepository} and controllers. Entities ({@code Inventory}
 * objects) should generally not leave the service layer. Public or protected methods that return entities are
 * intended for communication with other services.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;
    private final InventoryMapper mapper;
    private final CategoryService categoryService;

    /**
     * Retrieves all {@link Inventory} entities from repository. Each returned dto contains its {@link Category
     * categories}. This can be useful for displaying all items regardless of their characteristics.
     *
     * @return the list of dtos representing all found {@code Inventory} entities
     */
    public List<InventoryParentDto> getAllInventories() {
        return repository.findAll()
                         .stream()
                         .map(mapper::inventoryToInventoryParentDto)
                         .collect(Collectors.toList());
    }

    /**
     * Retrieves a {@link Inventory} entity with specified {@code id} from repository. The dto model contains its {@link Category
     * categories}.
     *
     * @param id the id of the searched {@code Inventory}
     * @return the dto representing the found {@code Inventory} entity
     */
    public InventoryParentDto getInventoryWithCategories(Long id) {
        return mapper.inventoryToInventoryParentDto(getInventoryFromRepository(id));
    }

    /**
     * Creates a new {@link Inventory} and stores it in database. The data of the new inventory should be passed as
     * data of a {@code InventoryRequestDto} in the request body.
     *
     * @param request the object containing data of {@code Inventory} entity to be created
     * @return the dto representing the created {@code Inventory} entity
     */
    public InventoryParentDto saveInventory(InventoryCreationRequestDto request) {
        Inventory inventory = mapper.inventoryCreationRequestDtoToInventory(request);
        addInventoryToCategories(inventory, request.getCategoriesSlugs());
        return mapper.inventoryToInventoryParentDto(repository.save(inventory));
    }

    /**
     * Updates an existing {@link Inventory} entity in database.
     * <p>
     * The fields that need to be changed have to be set in the {@code InventoryRequestDto} from the request body.
     * If a field of the request object is set to {@code null}, the corresponding field in the target entity is not
     * affected - it is not set to {@code null}. It is not possible to set a field of {@code Inventory} entity to
     * {@code null} through this method.
     *
     * @param id      the {@code id} of the contact entity to be updated
     * @param request the object containing new data used for the update
     * @return the dto representing the updated {@code Inventory} entity after the update
     */
    public InventoryParentDto updateInventory(Long id, InventoryUpdateRequestDto request) {
        Inventory inventoryToUpdate = getInventoryFromRepository(id);
        mapper.updateInventory(inventoryToUpdate, request);
        repository.save(inventoryToUpdate);
        return mapper.inventoryToInventoryParentDto(inventoryToUpdate);
    }

    /**
     * Adds an {@link Inventory} to a one or multiple existing {@link Category Categories}. The categories have
     * to be specified in {@code InventoryCategoryRequestDto}.
     *
     * @param id the {@code id} of the inventory entity which is to be added to the categories
     * @param request the object containing data specifying to which categories the item is to be added
     * @return the dto representing the updated {@code Inventory} entity after the addition
     */
    public InventoryParentDto addInventoryToCategories(Long id, InventoryCategoryRequestDto request) {
        Inventory inventoryToUpdate = getInventoryFromRepository(id);
        addInventoryToCategories(inventoryToUpdate, request.getCategoriesSlugs());
        return mapper.inventoryToInventoryParentDto(
            repository.save(inventoryToUpdate));
    }

    /**
     * Removes an {@link Inventory} from one or multiple existing {@link Category Categories}. The categories that
     * the item will be removed from have to be specified in {@code InventoryCategoryRequestDto}.
     *
     * @param id the {@code id} of the inventory entity which is to be removed from the categories
     * @param request the object containing data specifying which categories the item is to be removed from
     * @return the dto representing the updated {@code Inventory} entity after the removal
     */
    public InventoryParentDto removeInventoryFromCategories(Long id, InventoryCategoryRequestDto request) {
        Inventory inventoryToUpdate = getInventoryFromRepository(id);
        removeInventoryFromCategories(inventoryToUpdate, request.getCategoriesSlugs());
        return mapper.inventoryToInventoryParentDto(
            repository.save(inventoryToUpdate));
    }

    /**
     * Delete a {@link Inventory} entity in database.
     *
     * @param id the {@code id} of the inventory entity which is to be deleted
     */
    public void deleteInventory(Long id) {
        repository.deleteById(id);
    }

    /**
     * Retrieves {@code Inventory} entity from repository.
     * <p>
     * The returned entity is not intended to be used externally outside the service layer.
     *
     * @param id the {@code id} of the {@code Inventory} entity which is to be retrieved
     * @return the client entity
     */
    public Inventory getInventoryFromRepository(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new ResourceNotFoundException("Inventory not found in the database."));
    }

    private void addInventoryToCategories(Inventory inventoryToUpdate, Collection<String> categorySlugs) {
        if (categorySlugs != null) {
            categorySlugs
                .stream()
                .map(categoryService::getCategoryFromRepository)
                .filter(c -> !inventoryToUpdate.getCategories()
                                               .contains(c))
                .forEach(inventoryToUpdate::addCategory);
        }
    }

    private void removeInventoryFromCategories(Inventory inventoryToUpdate, Collection<String> categorySlugs) {
        if (categorySlugs != null) {
            categorySlugs
                .stream()
                .map(categoryService::getCategoryFromRepository)
                .filter(c -> inventoryToUpdate.getCategories()
                                              .contains(c))
                .forEach(inventoryToUpdate::removeCategory);
        }
    }

}
