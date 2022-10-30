package com.application.opencrm.client.service;

import com.application.opencrm.client.dto.ClientCreationRequestDto;
import com.application.opencrm.client.dto.ClientDto;
import com.application.opencrm.client.dto.ClientUpdateRequestDto;
import com.application.opencrm.client.mapper.*;
import com.application.opencrm.client.model.Client;
import com.application.opencrm.client.repository.ClientRepository;
import com.application.opencrm.common.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.catchException;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceUnitTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapperImpl clientMapper;

    @Test
    void GetClient_ShouldReturnSearchedMappedClientDto() {
        // Given
        long searchedId = 1L;
        Client foundClient = Client.builder()
                                   .id(searchedId)
                                   .build();
        ClientDto mappedClient = ClientDto.builder()
                                          .id(searchedId)
                                          .build();
        given(clientRepository.findById(searchedId)).willReturn(Optional.of(foundClient));
        given(clientMapper.clientToClientDto(any())).willReturn(mappedClient);

        // When
        ClientDto returnedClient = clientService.getClient(searchedId);

        // Then
        then(returnedClient.getId()).isEqualTo(searchedId);
    }

    @Test
    void GetClient_ShouldThrowException_IfClientIsNotFound() {
        // Given
        long searchedId = 1L;
        given(clientRepository.findById(searchedId)).willReturn(Optional.empty());

        // When
        Exception e = catchException(() -> clientService.getClient(searchedId));

        // Then
        then(e).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void SaveClient_ShouldCallRepositorySaveMethod() {
        // Given
        var request = ClientCreationRequestDto.builder()
                                              .build();
        given(clientMapper.clientCreationRequestDtoToClient(request)).willReturn(Client.builder()
                                                                                       .build());

        // When
        clientService.saveClient(request);

        // Then
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void UpdateClient_ShouldCallRepositorySaveMethod() {
        // Given
        long updatedClientId = 1L;
        var updateRequest = ClientUpdateRequestDto.builder()
                                                  .build();
        Client clientFromRepository = Client.builder()
                              .build();
        given(clientRepository.findById(updatedClientId)).willReturn(Optional.of(clientFromRepository));
        // When
        clientService.updateClient(updatedClientId, updateRequest);

        // Then
        verify(clientMapper, times(1)).updateClient(clientFromRepository, updateRequest);
        verify(clientRepository, times(1)).save(clientFromRepository);
    }

    @Test
    void DeleteClient_ShouldCallRepositoryDeleteMethod() {
        // Given
        long deletedClientId = 1L;

        // When
        clientService.deleteClient(deletedClientId);

        // Then
        verify(clientRepository, times(1)).deleteById(1L);
    }

    @Test
    void DeleteClient_ShouldThrowCustomException_IfDeleteMethodTrowsEmptyResultException() {
        // Given
        long nonExistingClientId = 1L;
        doThrow(new EmptyResultDataAccessException(1)).when(clientRepository).deleteById(nonExistingClientId);

        // When
        Exception e = catchException(() -> clientService.deleteClient(nonExistingClientId));

        // Then
        then(e).isInstanceOf(ResourceNotFoundException.class);
    }


}