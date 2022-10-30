package com.application.opencrm.demo;

import com.application.opencrm.client.dto.ContactCreationRequestDto;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("fakedata")
public class ContactDtoObjectMother {

    private final Faker faker;

    public ContactCreationRequestDto generateFakeContact() {
        return ContactCreationRequestDto.builder()
                                        .name("Home contact")
                                        .email(faker.internet()
                                                    .emailAddress())
                                        .phoneNumber(faker.phoneNumber()
                                                          .phoneNumber())
                                        .build();
    }

    public List<ContactCreationRequestDto> generateFakeContacts(int amount) {
        List<ContactCreationRequestDto> contacts = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            contacts.add(generateFakeContact());
        }
        return contacts;
    }

}
