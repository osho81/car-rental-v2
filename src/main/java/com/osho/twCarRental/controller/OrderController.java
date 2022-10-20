package com.osho.twCarRental.controller;

import com.osho.twCarRental.model.Customer;
import com.osho.twCarRental.model.Order;
import com.osho.twCarRental.repository.OrderRepository;
import com.osho.twCarRental.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;


    ////---------- READ (GET) -------------////

    @GetMapping("/orders/{id}") // NOT A PROJECT REQUIREMENT
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        return new ResponseEntity<Order>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping("/orders/byordernr/{ordernr}") // NOT A PROJECT REQUIREMENT
    public ResponseEntity<Order> getOrderByOrderNr(@PathVariable String ordernr) {
        return new ResponseEntity<Order>(orderService.getOrderByOrderNr(ordernr), HttpStatus.OK);
    }

    @GetMapping("/orders") // NOT A PROJECT REQUIREMENT
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<List<Order>>(orderService.getAllOrders(), HttpStatus.OK);
    }


    ////-------------- CREATE (SAVE) --------------////

    // "Se tidigare och aktiva bokningar GET /api/v1/myorders"
    @GetMapping("/myorders/{email}") // I added arg to url (note: email = username)
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable String email) {
        return new ResponseEntity<List<Order>>(orderService.getOrdersByUser(email), HttpStatus.OK);
    }



}
