package com.application.opencrm.order.service;

import com.application.opencrm.client.model.Client;
import com.application.opencrm.client.service.ClientService;
import com.application.opencrm.common.exception.ResourceNotFoundException;
import com.application.opencrm.order.dto.OrderCreationRequestDto;
import com.application.opencrm.order.dto.OrderDto;
import com.application.opencrm.order.mapper.OrderMapper;
import com.application.opencrm.order.model.Order;
import com.application.opencrm.order.model.OrderStatus;
import com.application.opencrm.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service handling operations on {@link Order} objects.
 * <p>
 * This class serves as a layer between {@link OrderRepository} and controllers.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final ClientService clientService;
    private final ItemService itemService;
    private final OrderingAddressService orderingAddressService;

    /**
     * Retrieves all {@link Order} entities from repository.
     *
     * @return the list of dtos representing all found {@code Order} entities
     */
    public List<OrderDto> getAllOrders() {
        return repository.findAll()
                         .stream()
                         .map(mapper::orderToOrderDto)
                         .collect(Collectors.toList());
    }

    /**
     * Retrieves a {@link Order} entity with specified {@code id} from repository.
     *
     * @param id the id of the searched {@code Order}
     * @return the dto representing the found {@code Order} entity
     */
    public OrderDto getOrder(Long id) {
        return mapper.orderToOrderDto(getOrderFromRepository(id));
    }

    /**
     * Creates a new {@link Order} and stores it in database. The data of the new order entity should be passed as data
     * of a {@code CategoryRequestDto} in the request body.
     *
     * @param request the object containing data of {@code Order} entity to be created
     * @return the dto representing the created {@code Order} entity
     */
    @Transactional
    public OrderDto saveOrder(OrderCreationRequestDto request) {
        Order orderToSave = createOrder(request);
        Order savedOrder = repository.save(orderToSave);
        return mapper.orderToOrderDto(savedOrder);
    }

    /**
     * Creates an {@link Order} based on the data contained in the {@link OrderCreationRequestDto}.
     *
     * @param request the object containing data of {@code Order} to be created
     * @return the created {@code Order}
     */
    private Order createOrder(OrderCreationRequestDto request) {
        Client client = clientService.getClientFromRepository(request.getClientId());
        Order order = Order.builder()
                           .client(client)
                           .items(new ArrayList<>())
                           .billingAddress(orderingAddressService.buildOrderingAddress(request.getBillingAddressId()))
                           .shippingAddress(orderingAddressService.buildOrderingAddress(request.getShippingAddressId()))
                           .status(OrderStatus.PROCESSING)
                           .build();
        request.getItems()
               .stream()
               .map(itemService::buildItem)
               .forEach(order::addItem);
        return order;
    }

    private Order getOrderFromRepository(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new ResourceNotFoundException("Order not found in the database."));
    }

}
