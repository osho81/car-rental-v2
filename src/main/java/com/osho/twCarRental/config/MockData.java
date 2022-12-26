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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.time.temporal.ChronoUnit.DAYS;

// Mock data to ease the presentation to product owner & customer

@Configuration
public class MockData {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
                                        CarRepository carRepository,
                                        OrderRepository orderRepository) {
        return args -> {

            //---------- Create several Customer mock data ----------//
            Customer customer1 = new Customer(
                    "12345", LocalDate.of(1970, Month.JULY, 7),
                    "dodu@gmail.com", "Donald", "Duck", "First street 1 Stockholm", null);
            Customer customer2 = new Customer(
                    "23456", LocalDate.of(1980, Month.AUGUST, 8),
                    "mimo1@gmail.com", "Mickey", "Mouse", "Second street 10 Stockholm", null);
            Customer customer3 = new Customer(
                    "34567", LocalDate.of(1990, Month.SEPTEMBER, 9),
                    "mimo2@gmail.com", "Minnie", "Mouse", "Second street 10 Stockholm", null);

            // Make sure already existing Customers are not added again (when ddl=update)
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
            // Save Customer mock data
            customerRepository.saveAll(mockCustomerList);

            //----------- Create several Car mock data -----------//
            Car car1 = new Car("abc123", "bmw", Car.Type.SEDAN, 2020, 400, null);
            Car car2 = new Car("bcd234", "audi", Car.Type.MINI, 2021, 300, null);
            Car car3 = new Car("cde345", "volvo", Car.Type.SUV, 2022, 500, null);

            // Make sure already existing Cars are not added again (when ddl=update)
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

            //---------- Create several Order mock data -----------//
            Order order1 = new Order(
                    "1001",
                    false,
                    LocalDateTime.now().minusDays(5).minusHours(5).minusMinutes(15), // Simulating real booking time
                    LocalDate.of(2022, Month.DECEMBER, 15),
                    LocalDate.of(2022, Month.DECEMBER, 19),
                    customer1.getId(),
                    car2.getId(),
                    0, 0, 0
                    // Price, numOfDays, priceInEuro is 0 here; later use setters to set them
            );
            Order order2 = new Order(
                    "1002",
                    false,
                    LocalDateTime.now().minusDays(10).minusHours(2), // Simulating real booking time
                    LocalDate.of(2023, Month.JANUARY, 2),
                    LocalDate.of(2023, Month.JANUARY, 8),
                    customer1.getId(),
                    car3.getId(),
                    0, 0, 0
            );
            Order order3 = new Order(
                    "1003",
                    false,
                    LocalDateTime.now().minusDays(7), // Simulating real booking time
                    LocalDate.of(2023, Month.JANUARY, 10),
                    LocalDate.of(2023, Month.JANUARY, 10),
                    customer1.getId(),
                    car1.getId(),
                    0, 0, 0
            );
            Order order4 = new Order(
                    "1004",
                    false,
                    LocalDateTime.now().minusDays(8), // Simulating real booking time
                    LocalDate.of(2023, Month.FEBRUARY, 14),
                    LocalDate.of(2023, Month.FEBRUARY, 19),
                    customer2.getId(),
                    car2.getId(),
                    0, 0, 0
            );
            Order order5 = new Order(
                    "1005",
                    false,
                    LocalDateTime.now().minusDays(1).minusHours(4).minusMinutes(30), // Simulating real booking time
                    LocalDate.of(2023, Month.JANUARY, 29),
                    LocalDate.of(2023, Month.JANUARY, 30),
                    customer2.getId(),
                    car1.getId(),
                    0, 0, 0
            );

            // Make sure already existing Orders are not added again (when ddl=update)
            Optional<Order> tempOrder1 = orderRepository.findByOrderNr("1001");
            if (tempOrder1.isEmpty()) {
                order1.setNumberOfDays((int) DAYS.between(order1.getFirstRentalDay(), order1.getLastRentalDay()) + 1);
                order1.setPrice(order1.getNumberOfDays() * carRepository.findById(order1.getCarId()).orElse(null).getDailySek());
                orderRepository.save(order1); // Instead of using list for saveAll
            } else {
                System.out.println(order1.getOrderNr() + " already exists");
            }
            Optional<Order> tempOrder2 = orderRepository.findByOrderNr("1002");
            if (tempOrder2.isEmpty()) {
                order2.setNumberOfDays((int) DAYS.between(order2.getFirstRentalDay(), order2.getLastRentalDay()) + 1);
                order2.setPrice(order2.getNumberOfDays() * carRepository.findById(order2.getCarId()).orElse(null).getDailySek());
                orderRepository.save(order2);
            } else {
                System.out.println(order2.getOrderNr() + " already exists");
            }
            Optional<Order> tempOrder3 = orderRepository.findByOrderNr("1003");
            if (tempOrder3.isEmpty()) {
                order3.setNumberOfDays((int) DAYS.between(order3.getFirstRentalDay(), order3.getLastRentalDay()) + 1);
                order3.setPrice(order3.getNumberOfDays() * carRepository.findById(order3.getCarId()).orElse(null).getDailySek());
                orderRepository.save(order3);
            } else {
                System.out.println(order3.getOrderNr() + " already exists");
            }
            Optional<Order> tempOrder4 = orderRepository.findByOrderNr("1004");
            if (tempOrder4.isEmpty()) {
                order4.setNumberOfDays((int) DAYS.between(order4.getFirstRentalDay(), order4.getLastRentalDay()) + 1);
                order4.setPrice(order4.getNumberOfDays() * carRepository.findById(order4.getCarId()).orElse(null).getDailySek());
                orderRepository.save(order4);
            } else {
                System.out.println(order4.getOrderNr() + " already exists");
            }

            Optional<Order> tempOrder5 = orderRepository.findByOrderNr("1005");
            if (tempOrder5.isEmpty()) {
                order5.setNumberOfDays((int) DAYS.between(order5.getFirstRentalDay(), order5.getLastRentalDay()) + 1);
                order5.setPrice(order5.getNumberOfDays() * carRepository.findById(order5.getCarId()).orElse(null).getDailySek());
                orderRepository.save(order5);
            } else {
                System.out.println(order5.getOrderNr() + " already exists");
            }


            //------------ Add saved mock Orders to pertinent Cars ----------.//
            Optional<Car> tempCar1a = carRepository.findByRegNr("abc123");
            if (tempCar1a.isPresent()) {
                // In two order
                tempCar1a.get().setOrdersOfCar(List.of(orderRepository.findByOrderNr("1003").orElse(null),
                        orderRepository.findByOrderNr("1005").orElse(null)));
                carRepository.save(tempCar1a.get());
            } else {
                System.out.println("Not applicable");
            }
            Optional<Car> tempCar2a = carRepository.findByRegNr("bcd234");
            if (tempCar2a.isPresent()) {
                // In two order
                tempCar2a.get().setOrdersOfCar(List.of(orderRepository.findByOrderNr("1001").orElse(null),
                        orderRepository.findByOrderNr("1004").orElse(null)));
                carRepository.save(tempCar2a.get());
            } else {
                System.out.println("Not applicable");
            }
            Optional<Car> tempCar3a = carRepository.findByRegNr("cde345");
            if (tempCar3a.isPresent()) {
                // In one orders
                tempCar3a.get().setOrdersOfCar(List.of(orderRepository.findByOrderNr("1002").orElse(null)));
                carRepository.save(tempCar3a.get());
            } else {
                System.out.println("Not applicable");
            }
        };
    }
}
