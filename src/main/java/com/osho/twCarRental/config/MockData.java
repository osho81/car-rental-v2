package com.osho.twCarRental.config;

import com.osho.twCarRental.model.Car;
import com.osho.twCarRental.model.Customer;
import com.osho.twCarRental.model.Order;
import com.osho.twCarRental.repository.CarRepository;
import com.osho.twCarRental.repository.CustomerRepository;
import com.osho.twCarRental.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class MockData {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
                                        CarRepository carRepository,
                                        OrderRepository orderRepository) {

        return args -> {

            // Create several Car mock data
            Customer customer1 = new Customer(
                    "12345",
                    LocalDate.of(1970, Month.JULY, 7),
                    "dodu@gmail.com",
                    "Donald",
                    "Duck",
                    "First street 1 Stockholm"
            );
            Customer customer2 = new Customer(
                    "23456",
                    LocalDate.of(1980, Month.AUGUST, 8),
                    "mimo1@gmail.com",
                    "Mickey",
                    "Mouse",
                    "Second street 10 Stockholm"
            );
            Customer customer3 = new Customer(
                    "34567",
                    LocalDate.of(1990, Month.SEPTEMBER, 9),
                    "mimo2@gmail.com",
                    "Minnie",
                    "Mouse",
                    "Second street 10 Stockholm"
            );
            ArrayList<Customer> mockCustomerList = new ArrayList<Customer>();

            // Initial method for not adding already existing Customers (when ddl=update)
            try {
                Optional<Customer> tempCust1 = customerRepository.findByEmail("dodu@gmail.com");
                mockCustomerList.add(customer1);
            } catch (RuntimeException e) {
                System.out.println(customer1.getEmail() + " already exists");
            }
            try {
                Optional<Customer> tempCust2 = customerRepository.findByEmail("mimo1@gmail.com");
                mockCustomerList.add(customer2);
            } catch (RuntimeException e) {
                System.out.println(customer2.getEmail() + " already exists");
            }
            try {
                Optional<Customer> tempCust3 = customerRepository.findByEmail("mimo2@gmail.com");
                mockCustomerList.add(customer3);
            } catch (RuntimeException e) {
                System.out.println(customer3.getEmail() + " already exists");
            }

            // Save customer mock data (in case not already exists)
            customerRepository.saveAll(mockCustomerList);

            // Create several Car mock data
            Car car1 = new Car(
                    "abc123",
                    "bmw",
                    "sedan",
                    2020,
                    500,
                    null
            );
            Car car2 = new Car(
                    "bcd234",
                    "audi",
                    "sedan",
                    2021,
                    600,
                    null
            );
            Car car3 = new Car(
                    "cde345",
                    "volvo",
                    "suv",
                    2022,
                    700,
                    null
            );
            ArrayList<Car> mockCarList = new ArrayList<Car>();

            // Initial method for not adding already existing Cars (when ddl=update)
            try {
                Optional<Car> tempCar1 = carRepository.findByRegNr("abc123");
                mockCarList.add(car1);
            } catch (RuntimeException e) {
                System.out.println(car1.getRegNr() + " already exists");
            }
            try {
                Optional<Car> tempCar1 = carRepository.findByRegNr("abc123");
                mockCarList.add(car1);
            } catch (RuntimeException e) {
                System.out.println(car1.getRegNr() + " already exists");
            }
            try {
                Optional<Car> tempCar1 = carRepository.findByRegNr("abc123");
                mockCarList.add(car1);
            } catch (RuntimeException e) {
                System.out.println(car1.getRegNr() + " already exists");
            }

            // Save car mock data
            carRepository.saveAll(List.of(car1, car2, car3));

            Order order1 = new Order(
                    LocalDateTime.of(2022, Month.AUGUST, 1, 14, 15),
                    LocalDate.of(2022, Month.DECEMBER, 15),
                    LocalDate.of(2022, Month.DECEMBER, 19),
                    customer1.getId(),
                    List.of(car1, car2, car3)
//                    new ArrayList<>(List.of(car1, car2))
            );
            Order order2 = new Order(
                    LocalDateTime.of(2022, Month.AUGUST, 5, 12, 45),
                    LocalDate.of(2023, Month.JANUARY, 2),
                    LocalDate.of(2023, Month.JANUARY, 8),
                    customer1.getId(),
                    List.of(car3)
//                    new ArrayList<>(List.of(car3))
            );
            // Save order mock data
            orderRepository.save(order1);
            orderRepository.save(order2);

            // Add saved Orders to pertinent Cars
            // ?????????????

        };

    }


}
