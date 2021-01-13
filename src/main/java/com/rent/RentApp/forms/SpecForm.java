package com.rent.RentApp.forms;

import javax.validation.constraints.NotBlank;

import com.rent.RentApp.configs.exceptions.constraints.NamePattern;
import com.rent.RentApp.models.CarType;
import com.rent.RentApp.models.FuelType;
import com.rent.RentApp.models.Specs;

public class SpecForm {

  @NotBlank
  private String name;
  @NotBlank
  private String description;
  @NamePattern
  private CarType carType;
  @NamePattern
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

  public Specs update(Specs spec) {

    spec.setName(this.name);
    spec.setDescription(this.description);
    spec.setCarType(this.carType);
    spec.setFuelType(this.fuelType);

    return spec;
  }

}
