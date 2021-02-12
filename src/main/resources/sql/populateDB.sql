INSERT INTO otus_sa.city (name)
VALUES ('Moscow'),
       ('Irkutsk');

INSERT INTO otus_sa.person (first_name, last_name, city_id, enabled)
VALUES ('Yuriy', 'Melnikov', 1000, true),
       ('Luba', 'Zholudeva', 1000, true),
       ('George', 'Kobichev', 1001, true),
       ('Sanya', 'Semin', 1001, false);

