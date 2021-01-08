package com.rent.RentApp.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.rent.RentApp.forms.CarForm;
import com.rent.RentApp.models.Cars;
import com.rent.RentApp.repositories.CarsRepository;
import com.rent.RentApp.services.Cars.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

  @Autowired
  private CarService carService;

  @GetMapping
  public Page<Cars> listCars(
      @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 20) Pageable pagination) {

    return this.carService.findPages(pagination);
  }

  @PostMapping
  public Cars createCar(@Valid @RequestBody CarForm form) {

    return this.carService.create(form);
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<Cars> updateCar(@PathVariable Long id, @Valid @RequestBody CarForm form) {
    Cars car = this.carService.update(id, form);

    return ResponseEntity.ok(car);

  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteCar(@PathVariable Long id) {
    carsRepository.deleteById(id);
  }
}
