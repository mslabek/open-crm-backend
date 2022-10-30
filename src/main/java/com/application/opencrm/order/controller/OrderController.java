package com.application.opencrm.order.controller;

import com.application.opencrm.order.dto.OrderCreationRequestDto;
import com.application.opencrm.order.dto.OrderDto;
import com.application.opencrm.order.model.Order;
import com.application.opencrm.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * {@code Rest controller} handling requests referring to {@link Order orders}.
 */
@Tag(name = "Order", description = "Operations referring to orders")
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Retrieves all {@link Order} entities from repository.
     *
     * @return the list of dtos representing all found {@code Order} entities
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_SALES', 'ROLE_INVENTORY')")
    @Operation(summary = "Retrieves all orders", description = "Required authorization role (any): ROLE_SALES, ROLE_INVENTORY")
    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Retrieves a {@link Order} entity with specified {@code id} from repository.
     *
     * @param id the id of the searched {@code Order}
     * @return the dto representing the found {@code Order} entity
     */
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_SALES', 'ROLE_INVENTORY')")
    @Operation(summary = "Retrieves an order", description = "Required authorization role (any): ROLE_SALES, ROLE_INVENTORY")
    @Parameter(name = "id", description = "the id of the order to which be retrieved")
    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Client with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public OrderDto getOrder(@PathVariable @Min(value = 1, message = "Order id cannot be smaller than 1") Long id) {
        return orderService.getOrder(id);
    }

    /**
     * Creates a new {@link Order} and stores it in database. The data of the new order entity should be passed as data
     * of a {@code CategoryRequestDto} in the request body.
     *
     * @param request the object specifying data of the {@code Order} entity to be created
     * @return the dto representing the created {@code Order} entity
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_SALES')")
    @Operation(summary = "Creates a new client", description = "Required authorization role (any): ROLE_SALES", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object specifying data of the order to be created"))
    @ApiResponse(responseCode = "201", description = "Order created successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Request lacks valid authentication", content = @Content)
    @ApiResponse(responseCode = "403", description = "Request not authorized (lack of sufficient authorization role)", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public OrderDto createOrder(@RequestBody @Valid OrderCreationRequestDto request) {
        return orderService.saveOrder(request);
    }

}
