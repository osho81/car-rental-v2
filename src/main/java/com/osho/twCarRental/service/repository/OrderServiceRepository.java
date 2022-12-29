package com.osho.twCarRental.service.repository;

import com.osho.twCarRental.model.Car;
import com.osho.twCarRental.model.Customer;
import com.osho.twCarRental.model.Order;
import java.util.List;

public interface OrderServiceRepository {

    List<Order> getMyOrders(Customer customer);
    Order orderCar(Order order);
    Order updateOrder(Order order);
    Order cancelOrder(Order order);

    // Added 221229 to adjust for frontend needs
    List<Car> getAllOrders();
}
