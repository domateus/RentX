package com.rent.RentApp.services.Cars;

import java.util.Optional;

import com.rent.RentApp.forms.CarForm;
import com.rent.RentApp.models.Cars;
import com.rent.RentApp.models.Specs;
import com.rent.RentApp.repositories.CarsRepository;
import com.rent.RentApp.repositories.SpecsRepository;
import com.rent.RentApp.services.Specs.CreateSpecService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  @Autowired
  private CarsRepository carsRepository;

  @Autowired
  private SpecsRepository specsRepository;

  @Autowired
  private CreateSpecService createSpec;

  public Page<Cars> findPages(Pageable pagination) {
    Page<Cars> cars = this.carsRepository.findAll(pagination);

    return cars;
  }

  public Cars create(CarForm form) {
    Optional<Specs> opSpecs = this.specsRepository.findById(form.getSpec_id());

    if (opSpecs.isEmpty()) {
      Specs newSpec = this.createSpec.create();

      form.setSpec_id(newSpec.getId());
    }
    System.out.println(form.getName());
    System.out.println(form.getBrand());
    System.out.println(form.getSpec_id());
    System.out.println(form.getDaily_value());
    Cars car = new Cars(form);

    this.carsRepository.save(car);

    return car;
  }

  public Cars update(Long id, CarForm form) {
    Optional<Cars> opCar = this.carsRepository.findById(id);

    if (opCar.isEmpty()) {
      return null;
    }

    Cars car = opCar.get();

    if (form.getSpec_id() != car.getSpecId()) {
      Optional<Specs> opSpecs = this.specsRepository.findById(form.getSpec_id());

      if (opSpecs.isEmpty()) {
        Specs newSpec = this.createSpec.create();

        car.setSpecId(newSpec.getId());
      }

      Specs spec = opSpecs.get();

      form.setSpec_id(spec.getId());
    }

    car = form.update(car);

    this.carsRepository.save(car);

    return car;
  }

  public void delete() {

  }

}
