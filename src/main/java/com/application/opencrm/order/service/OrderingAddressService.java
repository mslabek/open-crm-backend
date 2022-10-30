package com.application.opencrm.order.service;

import com.application.opencrm.client.model.Address;
import com.application.opencrm.client.service.AddressService;
import com.application.opencrm.order.mapper.OrderingAddressMapper;
import com.application.opencrm.order.model.OrderingAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service handling operations on {@link OrderingAddress} objects.
 */
@Service
@RequiredArgsConstructor
public class OrderingAddressService {

    private final AddressService addressService;
    private final OrderingAddressMapper mapper;

    /**
     * Creates {@code OrderingAddress} based on the data of a persisted {@link Address} specified by id. In order to
     * build an {@code OrderingAddress} through this method, you have to save an {@code Address} first and then refer to
     * it by {@code id}.
     *
     * @param addressId the {@code id} of {@code Address} in the repository that is to be the basis of the
     *                  {@code OrderingAddress}
     * @return the created {@code OrderingAddress}
     */
    protected OrderingAddress buildOrderingAddress(Long addressId) {
        Address address = addressService.getAddressFromRepository(addressId);
        return mapper.addressToOrderingAddress(address);
    }

}
