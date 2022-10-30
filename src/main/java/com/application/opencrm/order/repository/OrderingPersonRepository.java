package com.application.opencrm.order.repository;

import com.application.opencrm.order.model.OrderingAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to perform basic CRUD and SQL operations on {@link OrderingAddress} entities.
 */
@Repository
public interface OrderingPersonRepository extends CrudRepository<OrderingAddress, Long> {
}
