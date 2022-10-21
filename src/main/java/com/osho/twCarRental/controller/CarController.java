package com.osho.twCarRental.controller;

import com.osho.twCarRental.model.Car;
import com.osho.twCarRental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class CarController {

    @Autowired
    private CarService carService;

    //------------ READ (GET) --------------//

    @GetMapping("/cars/{id}") // NOT IN PROJECT REQUIREMENT
    public ResponseEntity<Car> getCarById(@PathVariable int id) {
        return new ResponseEntity<Car>(carService.getCarById(id), HttpStatus.OK);
    }

    // "Lista tillgängliga bilar GET /api/v1/cars"
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCustomers() {
        return new ResponseEntity<List<Car>>(carService.getAllCars(), HttpStatus.OK);
    }

    //----------- CREATE (SAVE)--------------//

    // "Lägga till fordon POST /api/v1/addcar"
    @PostMapping("/addcar")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        return new ResponseEntity<Car>(carService.addCar(car), HttpStatus.CREATED);
    }


    //-------------- UPDATE -----------------//

    // "Uppdatera fordon PUT /api/v1/updatecar"
    @PutMapping("/updatecar") // Find by passed in regNr, and update that car
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        return new ResponseEntity<Car>(carService.updateCarByRegnr(car), HttpStatus.CREATED);
    }

    @PutMapping("/updatecar/{id}") // Find by path-id, and update that car
    public ResponseEntity<Car> updateCar(@RequestBody Car car, @PathVariable int id) {
        return new ResponseEntity<Car>(carService.updateCar(id, car), HttpStatus.CREATED);
    }


    //-------------- DELETE -----------------//

    // "Ta bort fordon DELETE /api/v1/deletecar"

    @DeleteMapping("/deletecar") // My version with /{id}
    public ResponseEntity<String> deleteCar(@RequestBody Car car) {
        carService.deleteCar(car);
        return new ResponseEntity<String>("Car with reg. nr " + car.getRegNr() + " deleted.", HttpStatus.OK);
    }

    @DeleteMapping("/deletecar/{id}") // My version with /{id}
    public ResponseEntity<String> deleteCarById(@PathVariable int id) {
        carService.deleteCarById(id);
        return new ResponseEntity<String>("Car with id " + id + " deleted.", HttpStatus.OK);
    }


}
