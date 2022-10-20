package com.osho.twCarRental.service.repository;

import com.osho.twCarRental.model.Order;

import java.util.List;

public interface OrderServiceRepository {

    //-------- READ (GET) --------//
    Order getOrderById(int id);

    Order getOrderByOrderNr(String orderNr);

    List<Order> getAllOrders();

    List<Order> getOrdersByUser(String email);
}
