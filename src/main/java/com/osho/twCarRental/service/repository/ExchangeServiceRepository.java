package com.osho.twCarRental.service.repository;

import com.osho.twCarRental.valueobject.ResponseTemplateVO;
import org.json.JSONException;

import java.io.IOException;

public interface ExchangeServiceRepository {

//    ResponseTemplateVO getExchangeService(int orderId); // DISABLED 221224

    ResponseTemplateVO getExchangeWithoutMicroservice(int orderId) throws JSONException, IOException;
}
