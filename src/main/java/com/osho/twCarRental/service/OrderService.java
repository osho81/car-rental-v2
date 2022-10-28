package com.osho.twCarRental.service;

import com.osho.twCarRental.model.Car;
import com.osho.twCarRental.model.Customer;
import com.osho.twCarRental.model.Order;
import com.osho.twCarRental.repository.CarRepository;
import com.osho.twCarRental.repository.CustomerRepository;
import com.osho.twCarRental.repository.OrderRepository;
import com.osho.twCarRental.service.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class OrderService implements OrderServiceRepository {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    CarRepository carRepository;

    //-----------------------------------------------------------------------//
    //------------------------- PROJECT REQUIREMENTS ------------------------//
    //-----------------------------------------------------------------------//

    @Override
    public List<Order> getMyOrders(Customer customer) {

        // Find customer, then get the associated orders list
        Optional<Customer> foundCustomerById = customerRepository.findById(customer.getId());
        Optional<Customer> foundCustomerByEmail = customerRepository.findByEmail(customer.getEmail());

        if (foundCustomerById.isEmpty() && foundCustomerByEmail.isEmpty()) {
            throw new RuntimeException("Customer with id " + customer.getId()
                    + " or email " + customer.getEmail() + " not found");
        }
        // Else get customer by either customer id or email, then get customer's order list
        List<Order> customerOrderList = foundCustomerById.isPresent() ? foundCustomerById.get().getOrdersByCustomer() :
        foundCustomerByEmail.get().getOrdersByCustomer();
        return customerOrderList;
    }

    @Override
    public Order orderCar(Order order) {
        Optional<Order> foundOrder = orderRepository.findByOrderNr(order.getOrderNr());
        if (foundOrder.isPresent()) {
            throw new RuntimeException(order.getOrderNr() + " already exists.");
        } else {
            // Temp date/time field, in case request-body is missing time of order
            LocalDateTime tempDateTime;
            if (order.getOrderOrUpdateTime() == null) {
                tempDateTime = LocalDateTime.now();
            } else {
                tempDateTime = order.getOrderOrUpdateTime();
            }

            // Fetch duration between first and last rental day, include day 0 (i.e. add +1)
            // If dates are not chosen yet, temporary set to 1 day
            int tempNumOfDays = (order.getFirstRentalDay() != null && order.getLastRentalDay() != null) ?
                    ((int) DAYS.between(order.getFirstRentalDay(), order.getLastRentalDay()) + 1) : 1;

            // Calculate price, by getting numOfdays*price, if dates and carId are not missing in body
            double tempPrice;
            if (order.getPrice() == 0) {
                tempPrice = tempNumOfDays * carRepository.findById(order.getCarId()).orElse(null).getDailySek();
            } else {
                tempPrice = order.getPrice();
            }

            // Use "compulsory fields" from req-body: orderNr, first/last rent day, customerId, carId
            // and eventual temp-fields: order/update date, price, numOfDays
            return orderRepository.save(new Order(
                    order.getOrderNr(),
                    tempDateTime,
                    order.getFirstRentalDay(),
                    order.getLastRentalDay(),
                    order.getCustomerId(),
                    order.getCarId(),
                    tempPrice,
                    tempNumOfDays,
                    0 // price in euro is zero at first
            ));
        }
    }

    @Override
    public Order updateOrder(Order order) {

        // Get order to update with id if exists, else get with order nr if that exists
        Optional<Order> foundById = orderRepository.findById(order.getId());
        Optional<Order> foundByOrderNr = orderRepository.findByOrderNr(order.getOrderNr());
        if (foundById.isEmpty() && foundByOrderNr.isEmpty()) {
            throw new RuntimeException("Order with id " + order.getId()
                    + " or order nr " + order.getOrderNr() + " not found");
        }

        // Then, if either id or reg.nr exists, get order by one of them
        Order orderToUpdate = foundById.isPresent() ? orderRepository.findById(order.getId()).get() :
                orderRepository.findByOrderNr(order.getOrderNr()).get();

        //----- Add new value to each column, but keep old value if new is null/empty -----//
        orderToUpdate.setOrderNr(order.getOrderNr() == null ? orderToUpdate.getOrderNr() : order.getOrderNr());

        LocalDateTime tempDateTime; // See comments in orderCar() methods
        if (order.getOrderOrUpdateTime() == null) {
            tempDateTime = LocalDateTime.now();
        } else {
            tempDateTime = order.getOrderOrUpdateTime();
        }
        orderToUpdate.setOrderOrUpdateTime(tempDateTime);

        // Calculate numOfDays and check carId
        int tempNumOfDays = (order.getFirstRentalDay() != null && order.getLastRentalDay() != null) ?
                ((int) DAYS.between(order.getFirstRentalDay(), order.getLastRentalDay()) + 1) : 1;
        int tempCarId = order.getCarId() == 0 ? orderToUpdate.getCarId() : order.getCarId();

        // Calculate price, by getting number of days * price, if dates are not missing in body
        double tempPrice;
        if (carRepository.findById(tempCarId).isPresent()) {
            tempPrice = tempNumOfDays * carRepository.findById(tempCarId).orElse(null).getDailySek();
        } else {
            throw new RuntimeException("Please enter car id of ordered car.");
        }

        orderToUpdate.setFirstRentalDay(order.getFirstRentalDay() == null ? orderToUpdate.getFirstRentalDay() : order.getFirstRentalDay());
        orderToUpdate.setLastRentalDay(order.getLastRentalDay() == null ? orderToUpdate.getLastRentalDay() : order.getLastRentalDay());
        orderToUpdate.setCustomerId(order.getCustomerId() == 0 ? orderToUpdate.getCustomerId() : order.getCustomerId());
        orderToUpdate.setCarId(tempCarId);
        orderToUpdate.setPrice(tempPrice);
        orderToUpdate.setNumberOfDays(tempNumOfDays);

        // Save, i.e. update existing order, with new (and eventual old) passed in values
        return orderRepository.save(orderToUpdate);
    }


    @Override
    public void cancelOrder(Order order) {
        // Get order to update with id if exists, else get with order nr if that exists
        Optional<Order> foundById = orderRepository.findById(order.getId());
        Optional<Order> foundByOrderNr = orderRepository.findByOrderNr(order.getOrderNr());
        if (foundById.isEmpty() && foundByOrderNr.isEmpty()) {
            throw new RuntimeException("Order with id " + order.getId()
                    + " or order nr " + order.getOrderNr() + " not found");
        }

        // Then, if either id or order nr exists, get order by one of them
        Order orderToDelete = foundById.isPresent() ? orderRepository.findById(order.getId()).get() :
                orderRepository.findByOrderNr(order.getOrderNr()).get();

        // Save a "cancelled copy" of the deleted order; add "cancelled" to order nr
        // Use available method in this service class
        orderCar(new Order(
                "CANCELLED" + orderToDelete.getOrderNr(),
                LocalDateTime.now(),
                orderToDelete.getFirstRentalDay(),
                orderToDelete.getLastRentalDay(),
                orderToDelete.getCustomerId(),
                orderToDelete.getCarId(),
                orderToDelete.getPrice(),
                orderToDelete.getNumberOfDays(),
                orderToDelete.getPriceInEuro()
        ));

        // Delete original order
        orderRepository.delete(orderToDelete);


    }


    //-----------------------------------------------------------------------//
    //---------------------- NOT PROJECT REQUIREMENTS -----------------------//
    //-----------------------------------------------------------------------//

    @Override
    public List<Order> getOrdersByEmail(String email) {
        // Find customer, then get the associated orders list
        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);
        if (foundCustomer.isPresent()) {
            return foundCustomer.get().getOrdersByCustomer();
        } else {
            throw new RuntimeException("No user with email " + email);
        }
    }

    @Override
    public Order getOrderById(int id) {
        Optional<Order> foundOrder = orderRepository.findById(id);
        if (foundOrder.isPresent()) {
            return foundOrder.get();
        } else {
            throw new RuntimeException("Order with id " + id + " not found.");
        }
    }

    @Override
    public Order getOrderByOrderNr(String orderNr) {
        Optional<Order> foundOrder = orderRepository.findByOrderNr(orderNr);
        if (foundOrder.isPresent()) {
            return foundOrder.get();
        } else {
            throw new RuntimeException("Order with order nr " + orderNr + " not found.");
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Order order) {
        // Get order to update with id if exists, else get with reg.nr if that exists
        Optional<Order> foundById = orderRepository.findById(order.getId());
        Optional<Order> foundByOrderNr = orderRepository.findByOrderNr(order.getOrderNr());
        if (foundById.isEmpty() && foundByOrderNr.isEmpty()) {
            throw new RuntimeException("Order with id " + order.getId()
                    + " or order nr " + order.getOrderNr() + " not found");
        }

        // Then, if either id or order nr exists, get order by one of them, and delete
        Order orderToUpdate = foundById.isPresent() ? orderRepository.findById(order.getId()).get() :
                orderRepository.findByOrderNr(order.getOrderNr()).get();

        orderRepository.delete(orderToUpdate);
    }

    @Override
    public void deleteOrderById(int id) {
        orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order with id " + id + " not found."));
        orderRepository.deleteById(id);
    }


}
