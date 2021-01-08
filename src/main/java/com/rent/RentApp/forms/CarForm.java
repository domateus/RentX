package com.rent.RentApp.forms;

import javax.validation.constraints.NotBlank;

import com.rent.RentApp.models.Cars;

public class CarForm {

  @NotBlank
  private String name;
  @NotBlank
  private String daily_value;
  @NotBlank
  private String brand;
  private Long spec_id;

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

  public Cars update(Cars car) {

    car.setName(this.name);
    car.setBrand(this.brand);
    car.setDaily_value(this.daily_value);
    car.setSpecId(this.spec_id);

    return car;
  }

  public Long getSpec_id() {
    return spec_id;
  }

  public void setSpec_id(Long spec_id) {
    this.spec_id = spec_id;
  }

}
