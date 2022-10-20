package com.osho.twCarRental.service;

import com.osho.twCarRental.model.Customer;
import com.osho.twCarRental.model.Order;
import com.osho.twCarRental.repository.CustomerRepository;
import com.osho.twCarRental.repository.OrderRepository;
import com.osho.twCarRental.service.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceRepository {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;


    ////--------------- READ (GET) ----------------////

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
    public List<Order> getOrdersByUser(String email) {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);
        if (foundCustomer.isPresent()) {
            return foundCustomer.get().getOrdersByCustomer();
        } else {
            throw new RuntimeException("No user with email " + email);
        }
    }


    ////-------------- CREATE (SAVE) --------------////


}
