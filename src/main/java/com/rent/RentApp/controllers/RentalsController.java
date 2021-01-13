package com.rent.RentApp.controllers;

import javax.validation.Valid;

import com.rent.RentApp.dtos.RentalDto;
import com.rent.RentApp.forms.RentalForm;
import com.rent.RentApp.services.Rentals.RentalService;

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
  private RentalService rentalService;

  @GetMapping
  public Page<RentalDto> listRentals(
      @PageableDefault(sort = "start_date", direction = Direction.ASC, size = 20, page = 0) Pageable pagination) {
    Page<RentalDto> rentals = this.rentalService.findPages(pagination);

    return rentals;
  }

  @PostMapping
  public RentalDto createRental(@Valid @RequestBody RentalForm form) {
    RentalDto rental = this.rentalService.create(form);

    return rental;
  }

  @PutMapping("/{id}")
  public RentalDto updateRental(@Valid @RequestBody RentalForm form, @PathVariable Long id) {
    RentalDto rental = this.rentalService.update(form, id);

    return rental;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteRental(@PathVariable Long id) {
    this.rentalService.delete(id);
  }

}
