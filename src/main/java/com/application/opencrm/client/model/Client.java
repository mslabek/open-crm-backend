package com.application.opencrm.client.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a client.
 * <p>
 * Client here is understood as a person which has ordered some services or items from the company or has the
 * potential to make such orders.
 */
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_type")
    private ClientType clientType;

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Contact> contacts = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setClient(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setClient(null);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        contact.setClient(this);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
        contact.setClient(null);
    }

}
