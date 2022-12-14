--|| USERS ||--

CREATE TABLE users
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY,
    username VARCHAR(255),
    password VARCHAR(255),
    email    VARCHAR(255),
    enabled  BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE "role"
(
    id     BIGINT GENERATED BY DEFAULT AS IDENTITY,
    "name" VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id)
);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES users;
ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES role;

ALTER TABLE users
    ADD CONSTRAINT uq_user_name UNIQUE (username);
ALTER TABLE users
    ADD CONSTRAINT uq_user_email UNIQUE (email);
ALTER TABLE "role"
    ADD CONSTRAINT uq_role_name UNIQUE ("name");



--|| CLIENTS ||--

CREATE TABLE client
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY,
    "name"      VARCHAR(255),
    client_type VARCHAR(32),
    PRIMARY KEY (id)
);

CREATE TABLE address
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY,
    person_name     VARCHAR(255),
    country         VARCHAR(255),
    city            VARCHAR(255),
    region          VARCHAR(255),
    street          VARCHAR(255),
    building_number VARCHAR(255),
    postal_code     VARCHAR(255),
    client_id       BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE contact
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY,
    "name"       VARCHAR(255),
    email        VARCHAR(255),
    phone_number VARCHAR(255),
    client_id    BIGINT,
    PRIMARY KEY (id)
);

ALTER TABLE address
    ADD CONSTRAINT fk_address_client FOREIGN KEY (client_id) REFERENCES client;
ALTER TABLE contact
    ADD CONSTRAINT fk_contact_client FOREIGN KEY (client_id) REFERENCES client;


--|| INVENTORY ||--

CREATE TABLE inventory
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY,
    description   VARCHAR(255),
    "name"        VARCHAR(255),
    units         NUMERIC(19, 2),
    quantity_type VARCHAR(255),
    unit_price    NUMERIC(19, 2),
    PRIMARY KEY (id)
);

CREATE TABLE category
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY,
    "name"        VARCHAR(255),
    slug        VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE inventory_category
(
    inventory_id BIGINT NOT NULL,
    category_id  BIGINT NOT NULL,
    PRIMARY KEY (inventory_id, category_id)
);

ALTER TABLE inventory_category
    ADD CONSTRAINT fk_inventory_category_inventory FOREIGN KEY (inventory_id) REFERENCES inventory;
ALTER TABLE inventory_category
    ADD CONSTRAINT fk_inventory_category_category FOREIGN KEY (category_id) REFERENCES category;

ALTER TABLE inventory
    ADD CONSTRAINT uq_inventory_name UNIQUE ("name");
ALTER TABLE category
    ADD CONSTRAINT uq_category_slug UNIQUE (slug);
ALTER TABLE category
    ADD CONSTRAINT uq_category_name UNIQUE ("name");


--|| INVENTORY ||--

CREATE TABLE order_table
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY,
    created_at          TIMESTAMP,
    status              VARCHAR(255),
    billing_address_id  BIGINT,
    client_id           BIGINT,
    shipping_address_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE item
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY,
    "name"        VARCHAR(255),
    units         NUMERIC(19, 2),
    quantity_type VARCHAR(255),
    unit_price    NUMERIC(19, 2),
    inventory_id  BIGINT,
    order_id      BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE ordering_address
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY,
    building_number VARCHAR(255),
    city            VARCHAR(255),
    country         VARCHAR(255),
    person_name     VARCHAR(255),
    postal_code     VARCHAR(255),
    region          VARCHAR(255),
    street          VARCHAR(255),
    PRIMARY KEY (id)
);

ALTER TABLE order_table
    ADD CONSTRAINT fk_order_client FOREIGN KEY (client_id) REFERENCES client;
ALTER TABLE order_table
    ADD CONSTRAINT fk_order_ordering_address_shipping FOREIGN KEY (shipping_address_id) REFERENCES ordering_address;
ALTER TABLE order_table
    ADD CONSTRAINT fk_order_ordering_address_billing FOREIGN KEY (billing_address_id) REFERENCES ordering_address;
ALTER TABLE item
    ADD CONSTRAINT fk_item_order FOREIGN KEY (order_id) REFERENCES order_table;
ALTER TABLE item
    ADD CONSTRAINT fk_item_inventory FOREIGN KEY (inventory_id) REFERENCES inventory;

ALTER TABLE item
    ADD CONSTRAINT uq_item_name UNIQUE ("name");
