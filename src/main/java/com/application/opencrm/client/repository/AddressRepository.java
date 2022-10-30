package com.application.opencrm.client.repository;

import com.application.opencrm.client.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to perform basic CRUD and SQL operations on {@link Address} entities.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
