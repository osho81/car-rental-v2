
-- Use this for fast creation of customer and cars mock data during development

INSERT INTO CUSTOMERS (ssn, date_of_birth, email, first_name, last_name, address)
VALUES ('1999995', '1950-10-10', 'c1@gmail.com', 'Donald', 'Duck', 'data.sql street 1'),
       ('1888885', '1995-10-10', 'c2@gmail.com', 'Fatima', 'Ahmed', 'data.sql street 2'),
       ('1888886', '1995-10-11', 'c3@gmail.com', 'Cheb', 'Khaled', 'data.sql street 3'),
       ('1999995', '1982-10-10', 'c1@gmail.com', 'Mickey', 'Mouse', 'data.sql street 8'),
       ('1888885', '1995-10-10', 'c2@gmail.com', 'Minnie', 'Mouse', 'data.sql street 8'),
       ('1888886', '1985-10-11', 'c3@gmail.com', 'Meriam', 'Abdulla', 'data.sql street 9'),
       ('1888885', '1920-10-10', 'c2@gmail.com', 'Hani', 'Karam', 'data.sql street 10'),
       ('1888886', '1940-10-11', 'c3@gmail.com', 'Nancy', 'Ajram', 'data.sql street 15'),
       ('1999995', '2000-10-10', 'c1@gmail.com', 'Pelle', 'Larsson', 'data.sql street 17'),
       ('1888885', '1945-10-10', 'c2@gmail.com', 'Fatima', 'Ahmed', 'data.sql street 21');

INSERT INTO CARS (regnr, model, type, model_year, daily_sek)
VALUES ('abb122', 'Mercedes', 2, 2018, 500),
       ('bab456', 'Audi', 1, 2015, 400),
       ('cbb555', 'Bmw', 0, 2011, 300),
       ('dda222', 'Volvo', 4, 2022, 600),
       ('eed321', 'Mazda', 5, 2021, 500),
       ('fgh541', 'Toyota', 3, 2000, 200),
       ('gdv777', 'Bmw', 3, 2005, 200),
       ('hhh324', 'Mercedes', 1, 2018, 300),
       ('ijk987', 'Audi', 0, 2018, 350),
       ('jjj121', 'Mercedes', 4, 2022, 650);

-- Orders are instead logically created by commandLineRunner:
-- INSERT INTO ORDERS (order_nr, canceled, order_date, first_rental_day, last_rental_day,
--                    customer_id, car_id, price, num_of_days, price_in_eur)
-- VALUES ('1004', false, '2023-01-10T15:15:15', '2023-01-10', '2023-01-15', 3, 1, 0, 6, 0);


