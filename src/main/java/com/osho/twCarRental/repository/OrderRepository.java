package com.osho.twCarRental.repository;

import com.osho.twCarRental.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Customized method
    Optional<Order> findByOrderNr(String orderNr);
}
