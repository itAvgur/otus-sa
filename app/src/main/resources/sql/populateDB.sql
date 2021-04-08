INSERT INTO customers.city (name)
VALUES ('Moscow'),
       ('Irkutsk');

INSERT INTO customers.personal_data (login, email, first_name, last_name, city_id, birth_date)
VALUES ('itavgur', '1@ya.ru', 'Yuriy', 'Melnikov', 1000, '1979-12-15'),
       ('monkey', '2@ya.ru', 'Luba', 'Zholudeva', 1000, '1982-03-26'),
       ('drinker', '3@ya.ru', 'George', 'Kobichev', 1001, '1976-06-11'),
       ('lazyman', '4@ya.ru', 'Sanya', 'Semin', 1001, '1977-07-07');
