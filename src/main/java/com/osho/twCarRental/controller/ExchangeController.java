package com.osho.twCarRental.controller;

import com.osho.twCarRental.model.Order;
import com.osho.twCarRental.service.ExchangeService;
import com.osho.twCarRental.valueobject.ResponseTemplateVO;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    // Requirement: En microservice ska implementeras* GET /api/v1/exchange
//    @GetMapping("/exchange") // USER ROLE REQUIRED
    // Change 221224 to accept both Get & Post, since frontend js fetch used´s post with req-body
    @RequestMapping(value = "/exchange", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseTemplateVO getExchanged(@RequestBody Order order) throws JSONException, IOException { // Exceptions added 221224
        // calling service class method, to further reach the exchange microservice
//        return exchangeService.getExchangeService(order.getId()); // DISABLED 221224
        return exchangeService.getExchangeWithoutMicroservice(order.getId());

    }

}
