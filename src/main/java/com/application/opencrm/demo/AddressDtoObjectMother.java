package com.application.opencrm.demo;

import com.application.opencrm.client.dto.AddressCreationRequestDto;
import lombok.RequiredArgsConstructor;
import net.datafaker.Address;
import net.datafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("fakedata")
public class AddressDtoObjectMother {

    private final Faker faker;

    public AddressCreationRequestDto generateFakeAddress() {
        Address address = faker.address();
        return AddressCreationRequestDto.builder()
                                        .personName(faker.name()
                                                         .name())
                                        .country(address.country())
                                        .region(address.state())
                                        .city(address.city())
                                        .postalCode(address.postcode())
                                        .street(address.streetName())
                                        .buildingNumber(address.buildingNumber())
                                        .build();
    }

    public List<AddressCreationRequestDto> generateFakeAddresses(int amount) {
        List<AddressCreationRequestDto> addresses = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            addresses.add(generateFakeAddress());
        }
        return addresses;
    }

}

//    implementation 'org.postgresql:postgresql:42.5.0'