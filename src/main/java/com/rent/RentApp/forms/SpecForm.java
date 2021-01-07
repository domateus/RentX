package com.rent.RentApp.forms;

import com.rent.RentApp.models.CarType;
import com.rent.RentApp.models.FuelType;
import com.rent.RentApp.models.Specs;
import com.rent.RentApp.repositories.SpecsRepository;

public class SpecForm {

  private String name;
  private String description;
  private CarType carType;
  private FuelType fuelType;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CarType getCarType() {
    return carType;
  }

  public void setCarType(CarType carType) {
    this.carType = carType;
  }

  public FuelType getFuelType() {
    return fuelType;
  }

  public void setFuelType(FuelType fuelType) {
    this.fuelType = fuelType;
  }

  public Specs update(SpecsRepository repository, Long id) {
    Specs spec = repository.findById(id).get();

    spec.setName(this.name);
    spec.setDescription(this.description);
    spec.setCarType(this.carType);
    spec.setFuelType(this.fuelType);

    repository.save(spec);

    return spec;
  }

}
