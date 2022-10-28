package com.osho.twCarRental.service.repository;

import com.osho.twCarRental.model.Customer;
import com.osho.twCarRental.model.Order;

import java.util.List;

public interface OrderServiceRepository {

    List<Order> getMyOrders(Customer customer);
    Order orderCar(Order order);
    Order updateOrder(Order order);
    void cancelOrder(Order order);

}
