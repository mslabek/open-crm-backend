package com.application.opencrm.demo;

import com.application.opencrm.client.dto.ClientCreationRequestDto;
import com.application.opencrm.client.model.ClientType;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("fakedata")
public class ClientDtoObjectMother {

    private final AddressDtoObjectMother addressMother;
    private final ContactDtoObjectMother contactMother;
    private final Faker faker;

    ClientCreationRequestDto generateFakeClient() {
        return ClientCreationRequestDto.builder()
                                       .clientType(ClientType.INDIVIDUAL.toString())
                                       .name(faker.name()
                                                  .fullName())
                                       .addresses(addressMother.generateFakeAddresses(2))
                                       .contacts(contactMother.generateFakeContacts(2))
                                       .build();
    }

    List<ClientCreationRequestDto> generateFakeClients(int amount) {
        List<ClientCreationRequestDto> clients = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            clients.add(generateFakeClient());
        }
        return clients;
    }

}
