package com.osho.twCarRental.service;

import com.osho.twCarRental.model.Order;
import com.osho.twCarRental.repository.OrderRepository;
import com.osho.twCarRental.service.repository.ExchangeServiceRepository;
import com.osho.twCarRental.valueobject.Exchange;
import com.osho.twCarRental.valueobject.ResponseTemplateVO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

// Class with method calling Exchange-microservice, via the gateway

@Service
public class ExchangeService implements ExchangeServiceRepository {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    // THIS METHOD IS WHEN USING MICROSERVICE
    // (I.E. USED WITH CAR-RENTAL-V1)
//    @Override
//    public ResponseTemplateVO getExchangeService(int orderId) {
//        System.out.println("I am in TW main project exchange service method"); // Control print
//
//        ResponseTemplateVO vo = new ResponseTemplateVO(); // Enabling full/combined response
//
//        Order actualOrder = orderRepository.findById(orderId).get();
//
//        double amount = actualOrder.getPrice(); // Extract amount from found order obj
//
//        // Use app-name for microservice via gateway, instead of localhost/port; pass amount as url-var
//        Exchange exchange = restTemplate.getForObject("http://EXCHANGE-SERVICE/change/" + amount, Exchange.class);
//
//        // Optionally update & save price in euro in the order, for later reference
//        actualOrder.setPriceInEuro(exchange.getAmountInEur());
//        orderRepository.save(actualOrder);
//
//        vo.setOrder(actualOrder); // Add the order to full response
//        vo.setExchange(exchange); // Add the exchange details to full response
//
//        return vo;
//    }


    // THIS METHOD IS USED WHEN SKIPPING MICROSERVICE
    // (FOR MICROSERVICE APPROACH SEE CAR-RENTAL-V1)
    @Override
    public ResponseTemplateVO getExchangeWithoutMicroservice(int orderId) throws JSONException, IOException { // Exceptions added 221224
        System.out.println("I am in TW main project exchange service method without microservice"); // Control print

        ResponseTemplateVO vo = new ResponseTemplateVO(); // Enabling full/combined response
        Order actualOrder = orderRepository.findById(orderId).get();

        double amount = actualOrder.getPrice(); // Extract amount from found order obj

        // DISABLED 221224
        // Use app-name for microservice via gateway, instead of localhost/port; pass amount as url-var
//        Exchange exchange = restTemplate.getForObject("http://EXCHANGE-SERVICE/change/" + amount, Exchange.class);

        // INSTEAD OF MICROSERVICE; WE CALL THE HTTPREQUEST METHOD IN THIS CLASS HEREUNDER
        Exchange exchange = getExchangeInfo(amount);

        // Optionally update & save price in euro in the order, for later reference
        actualOrder.setPriceInEuro(exchange.getAmountInEur());
        orderRepository.save(actualOrder);

        vo.setOrder(actualOrder); // Add the order to full response
        vo.setExchange(exchange); // Add the exchange details to full response

        return vo;
    }

    // THIS METHOD IS COPIED FROM MICROSERVICE
    // (FOR ITS USE WITH MICROSERVICE APPROACH, SEE CAR-RENTAL-V1)

    // Declare url-values for http request required by external api
    public static final String APIKEY = "fJpXTOFjlF1oHbC2pUH1g4SbMI7NNy1z";
    public static final String BASE_URL = "https://api.apilayer.com/currency_data/";
    public static final String ENDPOINT = "convert";

    public Exchange getExchangeInfo(double amount) throws IOException, JSONException {
        System.out.println("I am in exchange-service Service class method"); // Control print

        String toCurrency = "EUR";
        String fromCurrency = "SEK";

        // The OkHttp client works well with spring
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Build request with url components; add path-var and other args to the path url
        // and add header with apikey retrieved from https://apilayer.com/
        Request request = new Request.Builder()
                .url(
                        BASE_URL
                                + ENDPOINT
                                + "?to=" +toCurrency
                                + "&from=" + fromCurrency
                                + "&amount=" + amount)
                .addHeader("apikey", APIKEY)
                .build();

        Response response = client.newCall(request).execute();
        JSONObject jo = new JSONObject(response.body().string()); // string(), NOT toString()

        // Pass in returned response values into exchange object
        Exchange exchangedReturned = new Exchange(amount, fromCurrency, toCurrency, jo.getDouble("result"));
        return exchangedReturned; // Return to calling service method

    }

}
