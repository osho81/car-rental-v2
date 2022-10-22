package com.osho.twCarRental.service;

import com.osho.twCarRental.model.Car;
import com.osho.twCarRental.repository.CarRepository;
import com.osho.twCarRental.service.repository.CarServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceRepository {

    @Autowired
    private CarRepository carRepository;

    //--------------- READ (GET) -----------------//

    @Override
    public Car getCarById(int id) {
        Optional<Car> foundCar = carRepository.findById(id);
        if (foundCar.isPresent()) {
            return foundCar.get();
        } else {
            throw new RuntimeException("Car with id " + id + " not found.");
        }
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    //-------------- CREATE (SAVE) --------------//

    @Override
    public Car addCar(Car car) {
        Optional<Car> foundByRegNr = carRepository.findByRegNr(car.getRegNr());
        if (foundByRegNr.isPresent()) { // Check if regNr is occupied
            throw new RuntimeException(car.getRegNr() + " already in our fleet.");
        } else {
            System.out.println(car.getModel() + " with reg. nr " + car.getRegNr() + " is added.");
            return carRepository.save(car);
        }
    }

    //---------------- UPDATE -----------------//

    // Update v1: find car by id or regNr-arg in request body, then update found car
    public Car updateCar(Car car) {

        // Get car to update with id if exists, else get with reg.nr if that exists
        Optional<Car> foundById = carRepository.findById(car.getId());
        Optional<Car> foundByRegNr = carRepository.findByRegNr(car.getRegNr());
        if (foundById.isEmpty() && foundByRegNr.isEmpty()) {
            throw new RuntimeException("Car with id " + car.getId()
                    + " or reg. nr " + car.getRegNr() + " not found");
        }

        // Then, if either id or reg.nr exists, get car by one of them
        Car carToUpdate = foundById.isPresent() ? carRepository.findById(car.getId()).get() :
                carRepository.findByRegNr(car.getRegNr()).get();

        // Add new value to each column, but keep old value if new is null/empty
        carToUpdate.setRegNr(car.getRegNr() == null ? carToUpdate.getRegNr() : car.getRegNr());
        carToUpdate.setModel(car.getModel() == null ? carToUpdate.getModel() : car.getModel());
        carToUpdate.setType(car.getType() == null ? carToUpdate.getType() : car.getType());
        // Convert local-date year to (sub)string, capture year, then to parse to int: model year is max next year
        carToUpdate.setModelYear(car.getModelYear() > Integer.parseInt(LocalDate.now().toString().substring(0,4))+1 ||
                car.getModelYear() == 0 ? carToUpdate.getModelYear() : car.getModelYear());
        carToUpdate.setDailySek(car.getDailySek() == 0 ? carToUpdate.getDailySek() : car.getDailySek());
//        carToUpdate.setOrdersOfCar(car.getOrdersOfCar());

        // Save, i.e. update existing car, with new (and eventual old) passed in values
        return carRepository.save(carToUpdate);
    }

    // Update v2: find car by id from path-url, then update found car
    @Override
    public Car updateCarById(int id, Car car) {
        // Get car with id to update, if exists
        Car carToUpdate = carRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Car with id " + id + " not found"));

        // Add new value to each column; but first check if reg. nr is occupied
        Optional<Car> foundByRegNr = carRepository.findByRegNr(car.getRegNr());
        if (foundByRegNr.isPresent()) {
            throw new RuntimeException(car.getRegNr() + " is occupied");
        } else if (car.getRegNr().isEmpty() || car.getRegNr() == null) {
            throw new RuntimeException("RegNr-field is empty");
        } else {
            // If regNr-field is not empty and not occupied: Set new regNr value
            carToUpdate.setRegNr(car.getRegNr());
        }

        carToUpdate.setModel(car.getModel());
        carToUpdate.setType(car.getType());
        carToUpdate.setModelYear(car.getModelYear());
        carToUpdate.setDailySek(car.getDailySek());
        carToUpdate.setOrdersOfCar(car.getOrdersOfCar());

        // Save, i.e. update existing car, with new passed in values
        return carRepository.save(carToUpdate);
    }


    //--------------- DELETE -----------------//

    @Override
    public void deleteCar(Car car) {
        // Directly using id from json body
//        carRepository.findById(car.getId()).orElseThrow(
//                () -> new RuntimeException("Car with id " + car.getId() + " not found."));
//        carRepository.deleteById(car.getId());

        // Finding car to delete with other field from json body
        Car carToDelete = carRepository.findByRegNr(car.getRegNr()).orElseThrow(
                () -> new RuntimeException("Car with reg. nr " + car.getRegNr() + " not found"));
        carRepository.deleteById(carToDelete.getId());
    }

    @Override
    public void deleteCarById(int id) {
        carRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Car with id " + id + " not found."));
        carRepository.deleteById(id);
    }


}
