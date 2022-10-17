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
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;

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

            ////------- Create several Customer mock data -------////
            Customer customer1 = new Customer(
                    "12345", LocalDate.of(1970, Month.JULY, 7),
                    "dodu@gmail.com", "Donald", "Duck", "First street 1 Stockholm");
            Customer customer2 = new Customer(
                    "23456", LocalDate.of(1980, Month.AUGUST, 8),
                    "mimo1@gmail.com", "Mickey", "Mouse", "Second street 10 Stockholm");
            Customer customer3 = new Customer(
                    "34567", LocalDate.of(1990, Month.SEPTEMBER, 9),
                    "mimo2@gmail.com", "Minnie", "Mouse", "Second street 10 Stockholm");

            // Initial method for not adding already existing Customers (when ddl=update)
            ArrayList<Customer> mockCustomerList = new ArrayList<Customer>();

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

            // Save Customer mock data (in case not already exists)
            customerRepository.saveAll(mockCustomerList);

            ////------- Create several Car mock data -------////
            Car car1 = new Car("abc123", "bmw", "sedan", 2020, 500, null);
            Car car2 = new Car("bcd234", "audi", "sedan", 2021, 600, null);
            Car car3 = new Car("cde345", "volvo", "suv", 2022, 700, null);

            // Initial method for not adding already existing Cars (when ddl=update)
            ArrayList<Car> mockCarList = new ArrayList<Car>();

            try {
                Optional<Car> tempCar1 = carRepository.findByRegNr("abc123");
                mockCarList.add(car1);
            } catch (RuntimeException e) {
                System.out.println(car1.getRegNr() + " already exists");
            }
            try {
                Optional<Car> tempCar2 = carRepository.findByRegNr("bcd234");
                mockCarList.add(car2);
            } catch (RuntimeException e) {
                System.out.println(car2.getRegNr() + " already exists");
            }
            try {
                Optional<Car> tempCar3 = carRepository.findByRegNr("cde345");
                mockCarList.add(car3);
            } catch (RuntimeException e) {
                System.out.println(car3.getRegNr() + " already exists");
            }

            // Save Car mock data
            carRepository.saveAll(mockCarList);

            ////------- Create several Order mock data -------////
            Order order1 = new Order(
                    "1001",
                    LocalDateTime.of(2022, Month.AUGUST, 1, 14, 15),
                    LocalDate.of(2022, Month.DECEMBER, 15),
                    LocalDate.of(2022, Month.DECEMBER, 19),
                    customer1.getId(),
                    2
//                    List.of(car1, car2, car3)
            );
            Order order2 = new Order(
                    "1002",
                    LocalDateTime.of(2022, Month.AUGUST, 5, 12, 45),
                    LocalDate.of(2023, Month.JANUARY, 2),
                    LocalDate.of(2023, Month.JANUARY, 8),
                    customer3.getId(),
                    3
//                    List.of(car3)
            );
            Order order3 = new Order(
                    "1003",
                    LocalDateTime.of(2022, Month.AUGUST, 9, 9, 45),
                    LocalDate.of(2023, Month.JANUARY, 10),
                    LocalDate.of(2023, Month.JANUARY, 10),
                    customer3.getId(),
                    3
//                    List.of(car3)
            );

            // Initial method for not adding already existing Cars (when ddl=update)
//            ArrayList<Order> mockOrderList = new ArrayList<Order>(); // Example without list

            try {
                Optional<Order> tempOrder1 = orderRepository.findByOrderNr("abc123");
                order1.setPrice(order1.getNumberOfDays() * carRepository.findById(order1.getCarId()).get().getDailySek());
//                mockCarList.add(car1);
                orderRepository.save(order2); // Instead of using list for saveAll
            } catch (RuntimeException e) {
                System.out.println(car1.getRegNr() + " already exists");
            }
            try {
                Optional<Order> tempOrder2 = orderRepository.findByOrderNr("abc123");
                order2.setPrice(order2.getNumberOfDays() * carRepository.findById(order2.getCarId()).get().getDailySek());
//                mockCarList.add(car2);
                orderRepository.save(order1);
            } catch (RuntimeException e) {
                System.out.println(car2.getRegNr() + " already exists");
            }
            try {
                Optional<Order> tempOrder3 = orderRepository.findByOrderNr("abc123");
                order3.setPrice(order3.getNumberOfDays() * carRepository.findById(order3.getCarId()).get().getDailySek());
//                mockCarList.add(car3);
                orderRepository.save(order3);
            } catch (RuntimeException e) {
                System.out.println(car3.getRegNr() + " already exists");
            }

            // Save Order mock data
//            orderRepository.saveAll(mockOrderList);

            ////-------- Add saved Orders to pertinent Cars -------////
            try {
                Optional<Car> tempCar2a = carRepository.findByRegNr("bcd234"); // In one order
                if (tempCar2a.isPresent()) {
                    tempCar2a.get().setCarOrders(List.of(orderRepository.findByOrderNr("1001").get()));
                    carRepository.save(tempCar2a.get());
                }
            } catch (RuntimeException e) {
                System.out.println("Not applicable");
            }
            try {
                Optional<Car> tempCar3a = carRepository.findByRegNr("cde345"); // In two orders
                if (tempCar3a.isPresent()) {
                    tempCar3a.get().setCarOrders(List.of(orderRepository.findByOrderNr("1002").get(),
                            orderRepository.findByOrderNr("1003").get()));
                    carRepository.save(tempCar3a.get());
                }
            } catch (RuntimeException e) {
                System.out.println("Not applicable");
            }
        };

    }


}
