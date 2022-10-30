package com.application.opencrm.demo;

import com.application.opencrm.client.service.ClientService;
import com.application.opencrm.inventory.dto.CategoryChildDto;
import com.application.opencrm.inventory.service.CategoryService;
import com.application.opencrm.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for generating fake data for demo purposes
 */
@Service
@RequiredArgsConstructor
@Profile("fakedata")
public class DataGenerator implements CommandLineRunner {

    private final ClientService clientService;
    private final ClientDtoObjectMother clientMother;
    private final InventoryDtoObjectMother inventoryMother;
    private final CategoryDtoObjectMother categoryMother;
    private final CategoryService categoryService;
    private final InventoryService inventoryService;

    private static final int CLIENT_AMOUNT = 20;
    private static final int INVENTORY_AMOUNT = 20;
    private static final int CATEGORY_AMOUNT = 2;


    @Override
    public void run(String... args) {
        if (clientService.getAllClients().isEmpty()) {
            clientMother.generateFakeClients(CLIENT_AMOUNT)
                        .forEach(clientService::saveClient);
            categoryMother.generateFakeCategories(CATEGORY_AMOUNT)
                          .forEach(categoryService::saveCategory);
            Set<String> currentCategories = categoryService.getAllCategories()
                                                           .stream()
                                                           .map(CategoryChildDto::getSlug)
                                                           .collect(Collectors.toSet());
            inventoryMother.generateFakeInventories(currentCategories, INVENTORY_AMOUNT)
                           .forEach(inventoryService::saveInventory);
        }
    }

}
