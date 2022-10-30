package com.application.opencrm.client.repository;

import com.application.opencrm.client.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to perform basic CRUD and SQL operations on {@link Contact} entities.
 */
@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
}
