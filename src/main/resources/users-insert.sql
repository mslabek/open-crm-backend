INSERT INTO users (id, username, password, email, enabled)
VALUES (1, 'Admin', '$2y$10$aADynZia3mrD/wy3hBLYVuumr1oh6fr8EA6nZnAo9KP2krekKH/jq', 'admin@gmail.com', true),
       (2, 'SalesTestUser', '$2y$10$z1a54VGFuPgEsxp9fnLUB.dzpe1Vm3ARDxe2swhkeTDGJ6BsL6wni', 'salesguy@gmail.com',
        true),
       (3, 'InventoryTestUser', '$2y$10$z1a54VGFuPgEsxp9fnLUB.dzpe1Vm3ARDxe2swhkeTDGJ6BsL6wni',
        'inventoryguy@gmail.com', true),
       (4, 'SuperUser', '$2y$10$z1a54VGFuPgEsxp9fnLUB.dzpe1Vm3ARDxe2swhkeTDGJ6BsL6wni',
        'sudo@gmail.com', true);

INSERT INTO role (id, name)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_SALES'),
       (3, 'ROLE_INVENTORY');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 1),
       (4, 2),
       (4, 3);