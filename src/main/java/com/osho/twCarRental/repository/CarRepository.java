package com.osho.twCarRental.repository;

import com.osho.twCarRental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
