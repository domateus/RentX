package com.rent.RentApp.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.rent.RentApp.forms.CarForm;
import com.rent.RentApp.models.Cars;
import com.rent.RentApp.repositories.CarsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarsController {

  @Autowired
  private CarsRepository carsRepository;

  @GetMapping
  public List<Cars> listCars() {
    return carsRepository.findAll();
  }

  @PostMapping
  public Cars createCar(@Valid @RequestBody CarForm form) {
    Cars newCar = new Cars(form);
    carsRepository.save(newCar);
    return newCar;
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<Cars> updateCar(@PathVariable Long id, @Valid @RequestBody CarForm form) {
    Optional<Cars> opCars = carsRepository.findById(id);

    if (opCars.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Cars car = form.update(id, carsRepository);

    return ResponseEntity.ok(car);

  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteCar(@PathVariable Long id) {
    carsRepository.deleteById(id);
  }
}
