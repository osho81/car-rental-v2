package com.osho.twCarRental.controller;

import com.osho.twCarRental.model.Car;
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


    //----------------- READ (GET) -----------------//

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

    // "Se tidigare och aktiva bokningar GET /api/v1/myorders"
    @GetMapping("/myorders/{email}") // I added arg to url (note: email = username)
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable String email) {
        return new ResponseEntity<List<Order>>(orderService.getOrdersByUser(email), HttpStatus.OK);
    }


    //----------------- CREATE (SAVE) ----------------//

    // "Boka hyrbil POST /api/v1/ordercar"
    @PostMapping("/ordercar")
    public ResponseEntity<Order> orderCar(@RequestBody Order order) {
        return new ResponseEntity<Order>(orderService.orderCar(order), HttpStatus.CREATED);
    }


    //---------------- UPDATE (SAVE) ----------------//

    // "Uppdatera order PUT /api/v1/updateorder"
    @PutMapping("/updateorder")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        return new ResponseEntity<Order>(orderService.updateOrder(order), HttpStatus.CREATED);
    }


    //----------------- DELETE --------------------//

    // "Avboka order PUT /api/v1/cancelorder"
    @DeleteMapping("/cancelorder")
    public ResponseEntity<String> cancelOrder(@RequestBody Order order) {
        orderService.cancelOrder(order);
        return new ResponseEntity<String>("Order with order nr " + order.getOrderNr() + " deleted.", HttpStatus.OK);
    }

    // My version with id/orderNr from req-body
    @DeleteMapping("/deleteorder")
    public ResponseEntity<String> deleteOrder(@RequestBody Order order) {
        orderService.deleteOrder(order);
        return new ResponseEntity<String>("Order with " + (order.getId() == 0 ?
                "order nr " : "with id " + order.getId() + " and order nr ") +
                order.getOrderNr() + " deleted.", HttpStatus.OK);
    }

    // My version with /{id}
    @DeleteMapping("/deleteorder/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable int id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<String>("Order with id " + id + " deleted.", HttpStatus.OK);
    }
}
