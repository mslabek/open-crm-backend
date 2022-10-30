package com.application.opencrm.order.repository;

import com.application.opencrm.order.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to perform basic CRUD and SQL operations on {@link Item} entities.
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
