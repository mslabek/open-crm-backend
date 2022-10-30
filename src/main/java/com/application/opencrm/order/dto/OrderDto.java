package com.application.opencrm.order.dto;

import com.application.opencrm.order.model.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

/**
 * Data transfer object of a {@link Order} for communication with processes external to the service layer.
 */
@Getter
@AllArgsConstructor
public class OrderDto {

    @Schema(description = "Order id", example = "1")
    private final Long id;

    @Schema(description = "List of ordered items")
    private final List<ItemDto> items;

    @Schema(description = "Billing address")
    private final OrderingAddressDto billingAddress;

    @Schema(description = "Shipping address")
    private final OrderingAddressDto shippingAddress;

    @Schema(description = "Current order status", allowableValues = {"PROCESSING", "SHIPPED", "CANCELLED"}, example = "PROCESSING")
    private final String status;

    // TODO example of INSTANT
    @Schema(description = "Time of creation of the order", example = "TODO")
    private final Instant createdAt;

}
