package com.application.opencrm.inventory.model;

import com.application.opencrm.order.model.Item;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing inventory.
 * <p>
 * Inventory is understood as a type of item that is quantifiable and has certain stock and a distinct name. In
 * this sense, {@link Item} is a fragment of the stock of the inventory, such that when the {@code Item} is sold or
 * restocked, it can be easily implemented.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    @ManyToMany
    @JoinTable(name = "inventory_category",
    joinColumns = @JoinColumn(name = "inventory_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @Column(name = "unit_price")
    private BigInteger unitPrice;

    @Column(name = "quantity_type")
    @Enumerated(EnumType.STRING)
    private QuantityType quantityType;

    private BigInteger units;

    public void addCategory(Category category) {
        categories.add(category);
        if (Hibernate.isInitialized(category.getInventories())) {
            category.getInventories().add(this);
        }
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        if (Hibernate.isInitialized(category.getInventories())) {
            category.getInventories().remove(this);
        }
    }

}
