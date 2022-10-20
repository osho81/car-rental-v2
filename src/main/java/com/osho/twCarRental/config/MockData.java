package com.osho.twCarRental.config;

import com.osho.twCarRental.model.Car;
import com.osho.twCarRental.model.CarType;
import com.osho.twCarRental.model.Customer;
import com.osho.twCarRental.model.Order;
import com.osho.twCarRental.repository.CarRepository;
import com.osho.twCarRental.repository.CustomerRepository;
import com.osho.twCarRental.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;

import javax.xml.transform.sax.SAXSource;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
                    "dodu@gmail.com", "Donald", "Duck", "First street 1 Stockholm", null);
            Customer customer2 = new Customer(
                    "23456", LocalDate.of(1980, Month.AUGUST, 8),
                    "mimo1@gmail.com", "Mickey", "Mouse", "Second street 10 Stockholm", null);
            Customer customer3 = new Customer(
                    "34567", LocalDate.of(1990, Month.SEPTEMBER, 9),
                    "mimo2@gmail.com", "Minnie", "Mouse", "Second street 10 Stockholm", null);

            // Initial method for not adding already existing Customers (when ddl=update)
            ArrayList<Customer> mockCustomerList = new ArrayList<Customer>();


            Optional<Customer> tempCust1 = customerRepository.findByEmail("dodu@gmail.com");
            if (tempCust1.isEmpty()) {
                mockCustomerList.add(customer1);
            } else {
                System.out.println(customer1.getEmail() + " already exists");
            }
            Optional<Customer> tempCust2 = customerRepository.findByEmail("mimo1@gmail.com");
            if (tempCust2.isEmpty()) {
                mockCustomerList.add(customer2);
            } else {
                System.out.println(customer2.getEmail() + " already exists");
            }
            Optional<Customer> tempCust3 = customerRepository.findByEmail("mimo2@gmail.com");
            if (tempCust3.isEmpty()) {
                mockCustomerList.add(customer3);
            } else {
                System.out.println(customer3.getEmail() + " already exists");
            }
            // Save Customer mock data (in case not already exists)
            customerRepository.saveAll(mockCustomerList);

            ////------- Create several Car mock data -------////
            // name() for converting enum CarType to string
            Car car1 = new Car("abc123", "bmw",  CarType.SEDAN.name(), 2020, 500, null);
            Car car2 = new Car("bcd234", "audi",  CarType.SEDAN.name(), 2021, 600, null);
            Car car3 = new Car("cde345", "volvo", CarType.SUV.name(), 2022, 700, null);

            // Initial method for not adding already existing Cars (when ddl=update)
            ArrayList<Car> mockCarList = new ArrayList<Car>();

            Optional<Car> tempCar1 = carRepository.findByRegNr("abc123");
            if (tempCar1.isEmpty()) {
                mockCarList.add(car1);
            } else {
                System.out.println(car1.getRegNr() + " already exists");
            }
            Optional<Car> tempCar2 = carRepository.findByRegNr("bcd234");
            if (tempCar2.isEmpty()) {
                mockCarList.add(car2);
            } else {
                System.out.println(car2.getRegNr() + " already exists");
            }
            Optional<Car> tempCar3 = carRepository.findByRegNr("cde345");
            if (tempCar3.isEmpty()) {
                mockCarList.add(car3);
            } else {
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
                    car2.getId()
//                    List.of(car1, car2, car3)
            );
            Order order2 = new Order(
                    "1002",
                    LocalDateTime.of(2022, Month.AUGUST, 5, 12, 45),
                    LocalDate.of(2023, Month.JANUARY, 2),
                    LocalDate.of(2023, Month.JANUARY, 8),
                    customer3.getId(),
                    car3.getId()
//                    List.of(car3)
            );
            Order order3 = new Order(
                    "1003",
                    LocalDateTime.of(2022, Month.AUGUST, 9, 9, 45),
                    LocalDate.of(2023, Month.JANUARY, 10),
                    LocalDate.of(2023, Month.JANUARY, 10),
                    customer3.getId(),
                    car3.getId()
//                    List.of(car3)
            );

            // Initial method for not adding already existing Cars (when ddl=update)
            Optional<Order> tempOrder1 = orderRepository.findByOrderNr("1001");
            if (tempOrder1.isEmpty()) {
//                   order1.setPrice(order1.getNumberOfDays() * carRepository.findById(order1.getCarId()).get().getDailySek());
                   order1.setPrice(order1.getNumberOfDays() * carRepository.findById(order1.getCarId()).orElse(null).getDailySek());
                orderRepository.save(order1); // Instead of using list for saveAll
            } else {
                System.out.println(order1.getOrderNr() + " already exists");
            }

            Optional<Order> tempOrder2 = orderRepository.findByOrderNr("1002");
            if (tempOrder2.isEmpty()) {
//                order2.setPrice(order2.getNumberOfDays() * carRepository.findById(order2.getCarId()).get().getDailySek());
                order2.setPrice(order2.getNumberOfDays() * carRepository.findById(order2.getCarId()).orElse(null).getDailySek());
                orderRepository.save(order2);
            } else {
                System.out.println(order2.getOrderNr() + " already exists");
            }

            Optional<Order> tempOrder3 = orderRepository.findByOrderNr("1003");
            if (tempOrder3.isEmpty()) {
//                order3.setPrice(order3.getNumberOfDays() * carRepository.findById(order3.getCarId()).get().getDailySek());
                order3.setPrice(order3.getNumberOfDays() * carRepository.findById(order3.getCarId()).orElse(null).getDailySek());
                orderRepository.save(order3);
            } else {
                System.out.println(order3.getOrderNr() + " already exists");
            }

            // Save Order mock data
//            orderRepository.saveAll(mockOrderList);


            ////-------- Add saved Orders to pertinent Cars -------////

            Optional<Car> tempCar2a = carRepository.findByRegNr("bcd234"); // In one order
            if (tempCar2a.isPresent()) {
                tempCar2a.get().setOrdersOfCar(List.of(orderRepository.findByOrderNr("1001").orElse(null)));
                carRepository.save(tempCar2a.get());
            } else {
                System.out.println("Not applicable");
            }

            Optional<Car> tempCar3a = carRepository.findByRegNr("cde345"); // In two orders
            if (tempCar3a.isPresent()) {
                tempCar3a.get().setOrdersOfCar(List.of(orderRepository.findByOrderNr("1002").orElse(null),
                        orderRepository.findByOrderNr("1003").orElse(null)));
                carRepository.save(tempCar3a.get());
            } else {
                System.out.println("Not applicable");
            }

        };
    }
}
