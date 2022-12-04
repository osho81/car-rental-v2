package com.osho.twCarRental.service.repository;

import com.osho.twCarRental.valueobject.ResponseTemplateVO;

public interface ExchangeServiceRepository {
    ResponseTemplateVO getExchangeService(int orderId);
}
