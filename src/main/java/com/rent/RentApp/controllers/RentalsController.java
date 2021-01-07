package com.rent.RentApp.controllers;

import com.rent.RentApp.forms.RentalForm;
import com.rent.RentApp.models.Cars;
import com.rent.RentApp.models.Rentals;
import com.rent.RentApp.models.Users;
import com.rent.RentApp.repositories.CarsRepository;
import com.rent.RentApp.repositories.RentalsRepository;
import com.rent.RentApp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/rentals")
public class RentalsController {

  @Autowired
  private RentalsRepository repository;

  @Autowired
  private CarsRepository carsRepository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public Page<Rentals> listRentals(
      @PageableDefault(sort = "start_date", direction = Direction.ASC, size = 20, page = 0) Pageable pagination) {

    Page<Rentals> rentals = this.repository.findFetchedRentals(pagination);

    return rentals;

  }

  @PostMapping
  public Rentals createRental(@RequestBody RentalForm form) {
    Cars car = carsRepository.findById(form.getCar_id()).get();
    Users client = userRepository.findById(form.getClient_id()).get();

    Rentals rental = new Rentals(form);

    rental.setCar(car);
    rental.setClient(client);

    repository.save(rental);

    return rental;
  }

  @PutMapping("/{id}")
  public Rentals updateRental(@RequestBody RentalForm form, @PathVariable Long id) {
    Cars car = carsRepository.findById(form.getCar_id()).get();
    Users client = userRepository.findById(form.getClient_id()).get();

    Rentals rental = repository.findFetchById(id).get();

    rental.setStart_date(form.getStart_date());
    rental.setEnd_date(form.getEnd_date());

    rental.setCar(car);
    rental.setClient(client);

    repository.save(rental);

    return rental;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteRental(@PathVariable Long id) {
    repository.deleteById(id);
  }

}
