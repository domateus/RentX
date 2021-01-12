package com.rent.RentApp.services.Cars;

import java.util.Optional;

import com.rent.RentApp.dtos.CarDto;
import com.rent.RentApp.dtos.SpecDto;
import com.rent.RentApp.forms.CarForm;
import com.rent.RentApp.models.Cars;
import com.rent.RentApp.repositories.CarsRepository;
import com.rent.RentApp.services.Specs.SpecService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  @Autowired
  private CarsRepository carsRepository;

  @Autowired
  private SpecService createSpec;

  public Page<CarDto> findPages(Pageable pagination) {
    Page<Cars> cars = this.carsRepository.findAll(pagination);

    return CarDto.convert(cars);

  }

  public Cars create(CarForm form) {
    Cars car = new Cars(form);
    this.createSpec.generateNew(form.getSpec_id(), car);

    this.carsRepository.save(car);

    return car;
  }

  public Cars update(Long id, CarForm form) {
    Optional<Cars> opCar = this.carsRepository.findById(id);

    if (opCar.isEmpty()) {
      return null;
    }
    Cars car = opCar.get();

    SpecDto spec = this.createSpec.generateNew(form.getSpec_id(), car);
    form.setSpec_id(Long.parseLong(spec.getId()));

    car = form.update(car);

    this.carsRepository.save(car);

    return car;
  }

  public void delete(Long id) {
    this.carsRepository.deleteById(id);

  }

}
