package com.application.opencrm.order.repository;

import com.application.opencrm.order.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository used to perform basic CRUD and SQL operations on {@link Order} entities.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();

}
