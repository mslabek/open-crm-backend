package com.application.opencrm.demo;

import com.application.opencrm.inventory.dto.InventoryCreationRequestDto;
import com.application.opencrm.inventory.model.QuantityType;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
@RequiredArgsConstructor
@Profile("fakedata")
public class InventoryDtoObjectMother {

    private final Faker faker;
    private final Random random;

    public InventoryCreationRequestDto generateFakeInventory(String name, Set<String> categorySlugs) {
        return InventoryCreationRequestDto.builder()
                                  .name(name)
                                  .quantityType(QuantityType.COUNTABLE.toString())
                                  .units(BigInteger.valueOf((random.nextInt(100000) + 1)))
                                  .unitPrice(BigInteger.valueOf((random.nextInt(1000) + 1)))
                                  .categoriesSlugs(getRandomSubset(categorySlugs, 2))
                                  .build();
    }

    public Set<String> getRandomSubset(Set<String> set, int amount) {
        if (set.size() < amount) {
            throw new DataGeneratorException("Not enough categories in the database. Decrease the number of minimum " +
                                           "categories per item or increase the number of categories in the database.");
        }
        Set<String> subset = new HashSet<>();
        List<String> mainList = new ArrayList<>(set);
        for (int i = 0; i < amount; i++) {
            subset.add(mainList.get(random.nextInt(set.size())));
        }
        return subset;
    }

    public List<InventoryCreationRequestDto> generateFakeInventories(Set<String> categorySlugs,
                                                                     int amount) {
        List<InventoryCreationRequestDto> inventories = new ArrayList<>();
        List<String> uniqueNames = generateInventoryNames(amount);
        for (int i = 0; i < amount; i++) {
            inventories.add(generateFakeInventory(uniqueNames.get(i), categorySlugs));
        }
        return inventories;
    }

    private List<String> generateInventoryNames(int amount) {
        Set<String> uniqueNames = new HashSet<>();
        while (uniqueNames.size() < amount) {
            uniqueNames.add(faker.commerce().productName());
        }
        return new ArrayList<>(uniqueNames);
    }

}
