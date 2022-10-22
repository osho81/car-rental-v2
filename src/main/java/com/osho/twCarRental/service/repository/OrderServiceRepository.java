package com.osho.twCarRental.service.repository;

import com.osho.twCarRental.model.Order;
import java.util.List;

public interface OrderServiceRepository {

    //------- READ (GET) -----//
    Order getOrderById(int id);
    Order getOrderByOrderNr(String orderNr);
    List<Order> getAllOrders();
    List<Order> getOrdersByUser(String email);

    //----- CREATE (SAVE) -----//
    Order orderCar(Order order);

    //----- UPDATE (SAVE) -----//
    Order updateOrder(Order order);

    //-------- DELETE ---------//
    // Delete but save a copy as cancelled order
    void cancelOrder(Order order);
    void deleteOrder(Order order);

    // Simple delete, by id
    void deleteOrderById(int id);
}
