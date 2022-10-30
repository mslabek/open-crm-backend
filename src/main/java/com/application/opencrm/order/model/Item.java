package com.application.opencrm.order.model;

import com.application.opencrm.inventory.model.Inventory;
import com.application.opencrm.inventory.model.QuantityType;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Entity representing an ordered item.
 * <p>
 * This is a different entity from {@link Inventory} since {@code item} is created at the moment of creating an
 * {@link Order} and doesn't change. If any of {@code Inventories} price changes, this shouldn't change the price of
 * already ordered items. Because of this, all the unchangeable fields are stored in {@code Item} entity, but a
 * reference to the original {@code Inventory} is also stored.
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private QuantityType quantityType;

    private BigInteger units;

    private BigInteger unitPrice;

}
