package com.application.opencrm.inventory.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a category of {@link Inventory}.
 * <p>
 * In documentation, {@code Inventory} is referred to be "belonging to" a {@code Category}, however their
 * relationship is coded as bidirectional.
 */
@Entity
@Getter
@Setter
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "categories")
    private Set<Inventory> inventories = new HashSet<>();

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String slug;

    private String description;

}
