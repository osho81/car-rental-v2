package com.osho.twCarRental.service;

import com.osho.twCarRental.model.Order;
import com.osho.twCarRental.repository.OrderRepository;
import com.osho.twCarRental.valueobject.Exchange;
import com.osho.twCarRental.valueobject.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ExchangeService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;


    public ResponseTemplateVO getExchangeService(int orderId) {
        System.out.println("I am in TW main project exchange service method");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Order actualOrder = orderRepository.findById(orderId).get();

        double amount = actualOrder.getPrice();

        // Use application name for microservice, instead of localhost...
        Exchange exchange = restTemplate.getForObject("http://EXCHANGE-SERVICE/exchange/" + amount, Exchange.class);

        vo.setOrder(actualOrder);
        vo.setExchange(exchange);
        return vo;
    }



}
