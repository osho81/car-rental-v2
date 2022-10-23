package com.osho.twCarRental.controller;

import com.osho.twCarRental.model.Car;
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

    //-----------------------------------------------------------------------//
    //------------------------- PROJECT REQUIREMENTS ------------------------//
    //-----------------------------------------------------------------------//

    // "Se tidigare och aktiva bokningar GET /api/v1/myorders"
    @GetMapping("/myorders")
    public ResponseEntity<List<Order>> getMyOrders(@RequestBody Customer customer) {
        return new ResponseEntity<List<Order>>(orderService.getMyOrders(customer), HttpStatus.OK);
    }

    // "Boka hyrbil POST /api/v1/ordercar"
    @PostMapping("/ordercar")
    public ResponseEntity<Order> orderCar(@RequestBody Order order) {
        return new ResponseEntity<Order>(orderService.orderCar(order), HttpStatus.CREATED);
    }

    // "Uppdatera order PUT /api/v1/updateorder"
    @PutMapping("/updateorder")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        return new ResponseEntity<Order>(orderService.updateOrder(order), HttpStatus.CREATED);
    }

    // "Avboka order PUT /api/v1/cancelorder"
    @DeleteMapping("/cancelorder")
    public ResponseEntity<String> cancelOrder(@RequestBody Order order) {
        orderService.cancelOrder(order);
        return new ResponseEntity<String>("Order with " +
                (order.getId() == 0 ? "order nr " : "with id " + order.getId() + " and order nr ") +
                order.getOrderNr() + " deleted.", HttpStatus.OK);
    }


    //-----------------------------------------------------------------------//
    //---------------------- NOT PROJECT REQUIREMENTS -----------------------//
    //-----------------------------------------------------------------------//

    @GetMapping("/myorders/{email}") // I added arg to url (note: email = username)
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable String email) {
        return new ResponseEntity<List<Order>>(orderService.getOrdersByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        return new ResponseEntity<Order>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping("/orders/byordernr/{ordernr}")
    public ResponseEntity<Order> getOrderByOrderNr(@PathVariable String ordernr) {
        return new ResponseEntity<Order>(orderService.getOrderByOrderNr(ordernr), HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<List<Order>>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteorder") // id/orderNr from req-body
    public ResponseEntity<String> deleteOrder(@RequestBody Order order) {
        orderService.deleteOrder(order);
        return new ResponseEntity<String>("Order with " +
                (order.getId() == 0 ? "order nr " : "with id " + order.getId() + " and order nr ") +
                order.getOrderNr() + " deleted.", HttpStatus.OK);
    }

    @DeleteMapping("/deleteorder/{id}") // id from path-url .../{id}
    public ResponseEntity<String> deleteOrderById(@PathVariable int id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<String>("Order with id " + id + " deleted.", HttpStatus.OK);
    }



}
