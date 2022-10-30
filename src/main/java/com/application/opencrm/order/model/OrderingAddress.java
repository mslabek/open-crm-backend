package com.application.opencrm.order.model;

import com.application.opencrm.client.model.Address;
import lombok.*;

import javax.persistence.*;

/**
 * Entity representing an ordering address.
 * <p>
 *  This is a different entity from {@link Address} since {@code OrderingAddress} is created at the moment of
 *  creating an {@link Order} and doesn't change. If any of {@code Addresses} data changes, this shouldn't change the
 *  data in orders that have already been made. Because of this, all the unchangeable fields are stored in {@code OrderingAddress}
 *  entity.
 */
@Entity
@Getter
@Setter
public class OrderingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_name")
    private String personName;
    private String country;
    private String city;
    private String region;
    private String street;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "postal_code")
    private String postalCode;

}
