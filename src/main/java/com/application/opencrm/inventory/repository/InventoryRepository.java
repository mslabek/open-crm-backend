package com.application.opencrm.inventory.repository;

import com.application.opencrm.inventory.model.Category;
import com.application.opencrm.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to perform basic CRUD and SQL operations on {@link Category} entities.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
