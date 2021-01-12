package com.rent.RentApp.services.Rentals;

import java.util.Optional;

import com.rent.RentApp.dtos.RentalDto;
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
import org.springframework.stereotype.Service;

@Service
public class RentalService {

  @Autowired
  private RentalsRepository rentalsRepository;

  @Autowired
  private CarsRepository carsRepository;

  @Autowired
  private UserRepository userRepository;

  public Page<RentalDto> findPages(Pageable pagination) {
    Page<Rentals> rentals = this.rentalsRepository.findFetchedRentals(pagination);

    return RentalDto.convert(rentals);
  }

  public RentalDto create(RentalForm form) {
    Optional<Cars> opCar = carsRepository.findById(form.getCar_id());
    Optional<Users> opClient = userRepository.findById(form.getClient_id());

    if (opCar.isEmpty() || opClient.isEmpty()) {
      return null;
    }
    Cars car = opCar.get();
    Users client = opClient.get();

    Rentals rental = new Rentals(form);

    rental.setCar(car);
    rental.setClient(client);

    car.addRental(rental);
    client.addRental(rental);

    this.rentalsRepository.save(rental);

    return new RentalDto(rental);
  }

  public RentalDto update(RentalForm form, Long id) {
    Optional<Rentals> opRental = this.rentalsRepository.findFetchById(id);
    Optional<Cars> opCar = carsRepository.findById(form.getCar_id());
    Optional<Users> opClient = userRepository.findById(form.getClient_id());

    if (opRental.isEmpty()) {
      return null;
    }

    if (opCar.isEmpty() || opClient.isEmpty()) {
      return null;
    }

    Rentals rental = opRental.get();
    Cars car = opCar.get();
    Users client = opClient.get();

    rental = form.update(rental, car, client);

    this.rentalsRepository.save(rental);

    return new RentalDto(rental);
  }

  public void delete(Long id) {
    this.rentalsRepository.deleteById(id);
  }

}
