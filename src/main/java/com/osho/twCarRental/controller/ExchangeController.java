package com.osho.twCarRental.controller;

import com.osho.twCarRental.model.Order;
import com.osho.twCarRental.service.ExchangeService;
import com.osho.twCarRental.valueobject.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping
    public ResponseTemplateVO getExchanged(@RequestBody Order order) {
        System.out.println("Inside TW main project method in exchange controller");
        return exchangeService.getExchangeService(order.getId());
    }

}
