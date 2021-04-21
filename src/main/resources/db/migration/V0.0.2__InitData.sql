INSERT INTO example_db.example_user (id, uuid, name, surname, email, cash, created_date, modified_date) VALUES (1, '6215e913-3712-4b66-993b-682905172d5a', 'John', 'Smith', 'jon.smith@gmail.com', 1000000, '2021-04-21 21:23:37', null);
INSERT INTO example_db.example_car (id, uuid, name, price, id_owner, created_date, modified_date) VALUES (1, '4ecdf0df-4c3d-4f06-9b75-9975d2346397', 'BMW 3 Series', 50000, 1, '2021-04-21 21:22:42', null);

INSERT INTO example_db.example_car (id, uuid, name, price, id_owner, created_date, modified_date) VALUES (2, 'b4f4417e-9bb8-4757-81e8-4cfb1e4baff1', 'Taycan', 1100000, null, '2021-04-21 21:26:01', null);

INSERT INTO example_db.example_order (id, uuid, id_user, id_car, price, created_date, modified_date) VALUES (1, 'f0bb9b63-1847-48b3-95a3-d5ab1d088672', 1, 2, 1100000, '2021-04-21 21:26:34',null );
