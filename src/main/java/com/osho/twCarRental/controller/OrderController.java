package com.osho.twCarRental.controller;

import com.osho.twCarRental.model.Customer;
import com.osho.twCarRental.model.Order;
import com.osho.twCarRental.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Requirement: "Se tidigare och aktiva bokningar GET /api/v1/myorders"
    @GetMapping("/myorders") // USER ROLE REQUIRED
    public ResponseEntity<List<Order>> getMyOrders(@RequestBody Customer customer) {
        return new ResponseEntity<List<Order>>(orderService.getMyOrders(customer), HttpStatus.OK);
    }

    // Requirement: "Boka hyrbil POST /api/v1/ordercar"
    @PostMapping("/ordercar") // USER ROLE REQUIRED
    public ResponseEntity<Order> orderCar(@RequestBody Order order) {
        return new ResponseEntity<Order>(orderService.orderCar(order), HttpStatus.CREATED);
    }

    // Requirement: "Uppdatera order PUT /api/v1/updateorder"
    @PutMapping("/updateorder") // USER ROLE REQUIRED
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        return new ResponseEntity<Order>(orderService.updateOrder(order), HttpStatus.CREATED);
    }

    // Requirement: "Avboka order PUT /api/v1/cancelorder"
    @DeleteMapping("/cancelorder") // ADMIN ROLE REQUIRED
    public ResponseEntity<String> cancelOrder(@RequestBody Order order) {
        orderService.cancelOrder(order);
        return new ResponseEntity<String>("Order with " +
                (order.getId() == 0 ? "order nr " : "id " + order.getId() + " and/or order nr ") +
                order.getOrderNr() + " deleted.", HttpStatus.OK);
    }

}
