package com.application.opencrm.order.dto;

import com.application.opencrm.order.model.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * Data transfer object of a request to create a {@link Order}.
 */
@Builder
@Getter
@AllArgsConstructor
public class OrderCreationRequestDto {

    @Schema(description = "Id of the client who made the order", required = true, example = "1")
    @Min(value = 1, message = "Client id cannot be smaller than 1")
    private final Long clientId;

    @Schema(description = "Id of the address from which the billing address will be created", required = true, example = "1")
    @Min(value = 1, message = "Billing address id cannot be smaller than 1")
    private final Long billingAddressId;

    @Schema(description = "Id of the address from which the shipping address will be created", required = true, example = "1")
    @Min(value = 1, message = "Shipping address id cannot be smaller than 1")
    private final Long shippingAddressId;

    @Schema(description = "List of creation requests containing data for new order items", required = true)
    private final List<ItemCreationRequestDto> items;

}
