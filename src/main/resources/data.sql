-- Use this for fast creation of customer and cars mock data during development
-- Mockdata.java creates initially data, use this if needs more mock data
-- Select customers, cars, or orders, and click run; select part to create
-- (Or just select to run the whole file)

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
       ('bal457', 'Audi', 1, 2015, 400),
       ('cbb555', 'Bmw', 0, 2011, 300),
       ('dda222', 'Volvo', 4, 2022, 600),
       ('eed321', 'Mazda', 5, 2021, 500),
       ('fgh541', 'Toyota', 3, 2000, 200),
       ('gdv777', 'Bmw', 3, 2005, 200),
       ('hhh324', 'Mercedes', 1, 2018, 300),
       ('ijk987', 'Audi', 0, 2018, 350),
       ('jjj121', 'Mercedes', 4, 2022, 650);

-- Primarily, orders are logically created by commandLineRunner:
INSERT INTO ORDERS (order_nr, canceled, order_date, first_rental_day, last_rental_day,
                    customer_id, car_id, price, num_of_days, price_in_eur)
VALUES ('2001', false, '2022-11-11T12:00:00', '2023-01-14', '2023-01-16', 1, 2, 1200, 3, 0),
       ('2002', false, '2022-11-12T13:00:00', '2023-01-13', '2023-01-15', 1, 5, 1500, 3, 0),
       ('2003', false, '2022-10-25T08:10:00', '2023-01-10', '2023-01-14', 7, 3, 2500, 5, 0),
       ('2004', false, '2022-11-18T17:12:15', '2023-02-11', '2023-02-12', 5, 9, 400, 2, 0),
       ('2005', false, '2022-12-28T11:11:15', '2022-12-12', '2022-12-12', 2, 4, 500, 1, 0),
       ('2006', false, '2022-11-18T17:35:00', '2023-02-25', '2023-02-25', 1, 5, 400, 1, 0),
       ('2007', true, '2022-12-17T19:40:00', '2022-11-25', '2022-11-27', 1, 1, 1200, 3, 0),
       ('2008', false, '2022-12-18T10:10:15', '2023-02-25', '2023-02-25', 2, 3, 500, 1, 0);


