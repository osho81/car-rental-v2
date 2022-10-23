package com.osho.twCarRental.service.repository;

import com.osho.twCarRental.model.Customer;
import com.osho.twCarRental.model.Order;
import java.util.List;

public interface OrderServiceRepository {

    //---- PROJECT REQUIREMENTS ----//
    List<Order> getMyOrders(Customer customer);
    Order orderCar(Order order);
    Order updateOrder(Order order);
    void cancelOrder(Order order);


    //-- NOT PROJECT REQUIREMENTS --//
    List<Order> getOrdersByEmail(String email);
    Order getOrderById(int id);
    Order getOrderByOrderNr(String orderNr);
    List<Order> getAllOrders();
    void deleteOrder(Order order);
    void deleteOrderById(int id);

}
