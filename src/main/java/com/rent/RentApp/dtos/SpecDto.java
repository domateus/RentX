package com.rent.RentApp.dtos;

import com.rent.RentApp.models.Specs;

import org.springframework.data.domain.Page;

public class SpecDto {

  private String name;
  private String id;
  private String carType;
  private String fuelType;
  private String description;

  public SpecDto(Specs spec) {
    this.name = spec.getName();
    this.id = String.valueOf(spec.getId());
    this.carType = String.valueOf(spec.getCarType());
    this.fuelType = String.valueOf(spec.getFuelType());
    this.description = spec.getDescription();
  }

  public SpecDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCarType() {
    return carType;
  }

  public void setCarType(String carType) {
    this.carType = carType;
  }

  public String getFuelType() {
    return fuelType;
  }

  public void setFuelType(String fuelType) {
    this.fuelType = fuelType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public static Page<SpecDto> convert(Page<Specs> specs) {
    return specs.map(SpecDto::new);
  }

}
