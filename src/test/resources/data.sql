--Addresses
INSERT INTO addresses(id, address_type, city, house_number, post_code, street)
    VALUES (1, 'MAILING', 'Kraków', '23', '32-100', 'Kalwaryjska');
INSERT INTO addresses(id, address_type, city, house_number, post_code, street)
    VALUES (2, 'RESIDENCE', 'Gdynia', '123', '12-123', 'Krakowska');
INSERT INTO addresses(id, address_type, city, house_number, post_code, street)
    VALUES (3, 'SHIP', 'Warszawa', '23/12', '02-100', 'Warszawska');
INSERT INTO addresses(id, address_type, city, house_number, post_code, street)
    VALUES (4, 'RESIDENCE', 'Wrocław', '89/12', '40-115', 'Wadowicka');
INSERT INTO addresses(id, address_type, city, house_number, post_code, street)
    VALUES (5, 'MAILING', 'Kraków', '2', '30-100', 'Rondo Mateczne');
--Categories
INSERT INTO categories(id, name)
    VALUES (1, 'Fotele');
INSERT INTO categories(id, name)
    VALUES (2, 'Kanapy');
INSERT INTO categories(id, name)
    VALUES (3, 'Stoły');
--Products
INSERT INTO products(id, name, description, category_id)
    VALUES(1, 'Uszak', 'Opis produktu', 1);
INSERT INTO products(id, name, description, category_id)
    VALUES(2, 'Viking', 'Opis produktu drugiego', 1);
INSERT INTO products(id, name, description, category_id)
    VALUES(3, 'MB-01', 'Opis produktu trzeciego', 2);