package com.application.opencrm.user.repository;

import com.application.opencrm.order.model.Item;
import com.application.opencrm.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository used to perform basic CRUD and SQL operations on {@link Item} entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
