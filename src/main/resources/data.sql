
-- Use this for fast creation of customer and cars mock data during development

INSERT INTO CUSTOMERS (ssn, date_of_birth, email, first_name, last_name, address)
VALUES ('199999511', '1950-10-10', 'c1@gmail.com', 'Donald', 'Duck', 'cool street 1'),
       ('188888522', '1995-10-10', 'c2@gmail.com', 'Fatima', 'Ahmed', 'amazing street 2'),
       ('188888633', '1995-10-11', 'c3@gmail.com', 'Cheb', 'Khaled', 'fantastic street 3'),
       ('199999544', '1982-10-10', 'djt@gmail.com', 'Mickey', 'Mouse', 'happy street 8'),
       ('188888555', '1995-10-10', 'wnwr@gmail.com', 'Minnie', 'Mouse', 'happy street 8'),
       ('188888666', '1985-10-11', 'rwrh@gmail.com', 'Meriam', 'Abdulla', 'some street 9'),
       ('188888577', '1920-10-10', 'cwe@gmail.com', 'Hani', 'Karam', 'main street 10'),
       ('188888688', '1940-10-11', 'qeg@gmail.com', 'Nancy', 'Ajram', 'some street 15'),
       ('199999599', '2000-10-10', 'uyt@gmail.com', 'Pelle', 'Larsson', 'some street 17'),
       ('188888500', '1945-10-10', 'otf@gmail.com', 'Fatima', 'Ahmed', 'cool street 21');

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


