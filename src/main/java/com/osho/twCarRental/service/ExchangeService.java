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


    public ResponseTemplateVO getExchangeInfo(int orderId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Order actualOrder = orderRepository.findById(orderId).orElseGet(
                () -> {
                    throw new RuntimeException("Order with id " + orderId + " not found");
                });

        // Use application name for microservice, instead of localhost...
        // Pass in Exchange class, to extract needed fields from at the exchange microservice side
        Exchange exchange = restTemplate.getForObject("http://EXCHANGE-SERVICE//exchange/sekeur", Exchange.class);

        vo.setOrder(actualOrder);
        vo.setExchange(exchange);
        return vo;
    }



}
