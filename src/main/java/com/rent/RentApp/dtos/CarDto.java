package com.rent.RentApp.dtos;

import com.rent.RentApp.models.Cars;

import org.springframework.data.domain.Page;

public class CarDto {

  private String name;
  private String brand;
  private String daily_value;
  private String spec_id;

  public CarDto(Cars car) {
    this.name = car.getName();
    this.brand = car.getBrand();
    this.daily_value = car.getDaily_value();
    if (car.getSpec() != null) {
      this.spec_id = String.valueOf(car.getSpecId());
    } else {
      this.spec_id = "null";
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getDaily_value() {
    return daily_value;
  }

  public void setDaily_value(String daily_value) {
    this.daily_value = daily_value;
  }

  public String getSpec_id() {
    return spec_id;
  }

  public void setSpec_id(String spec_id) {
    this.spec_id = spec_id;
  }

  public static Page<CarDto> convert(Page<Cars> cars) {
    return cars.map(CarDto::new);
  }

}
