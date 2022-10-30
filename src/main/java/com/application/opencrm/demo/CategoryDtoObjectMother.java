package com.application.opencrm.demo;

import com.application.opencrm.inventory.dto.CategoryCreationRequestDto;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Profile("fakedata")
public class CategoryDtoObjectMother {

    private final Faker faker;

    public CategoryCreationRequestDto generateFakeCategory() {
        return CategoryCreationRequestDto.builder()
                                 .name(faker.industrySegments().sector())
                                 .build();
    }

    public Set<CategoryCreationRequestDto> generateFakeCategories(int amount) {
        Set<CategoryCreationRequestDto> categories = new HashSet<>();
        while (categories.size() < amount) {
            categories.add(generateFakeCategory());
        }
        return categories;
    }

}
