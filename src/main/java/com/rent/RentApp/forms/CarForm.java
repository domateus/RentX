package com.rent.RentApp.forms;

import com.rent.RentApp.models.Cars;
import com.rent.RentApp.repositories.CarsRepository;

public class CarForm {

  private String name;
  private String daily_value;
  private String brand;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDaily_value() {
    return daily_value;
  }

  public void setDaily_value(String daily_value) {
    this.daily_value = daily_value;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public Cars update(Long id, CarsRepository carsRepository) {
    Cars car = carsRepository.getOne(id);

    car.setName(this.name);
    car.setBrand(this.brand);
    car.setDaily_value(this.daily_value);

    return car;
  }

}
