package com.application.opencrm.client.repository;

import com.application.opencrm.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository used to perform basic CRUD and SQL operations on {@link Client} entities.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAll();

}
