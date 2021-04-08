INSERT INTO customers.city (name)
VALUES ('Moscow'),
       ('Irkutsk');

INSERT INTO customers.personal_data (login, email, first_name, last_name, city_id, birth_date)
VALUES ('itavgur', '1@ya.ru', 'Yuriy', 'Melnikov', 1000, null),
       ('monkey', '2@ya.ru', 'Luba', 'Zholudeva', 1000, null),
       ('drinker', '3@ya.ru', 'George', 'Kobichev', 1001, null),
       ('lazyman', '4@ya.ru', 'Sanya', 'Semin', 1001, null);
