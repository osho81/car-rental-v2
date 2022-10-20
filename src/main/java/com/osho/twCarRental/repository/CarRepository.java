package com.osho.twCarRental.repository;

import com.osho.twCarRental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {

    // Customized methods
    Optional<Car> findByRegNr(String regNr);


}
