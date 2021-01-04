package com.rent.RentApp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Specs {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Cars> cars = new ArrayList<>();

  private String name;

  private String description;

  private String icon;

  @Enumerated(EnumType.STRING)
  private CarType carType;

  @Enumerated(EnumType.STRING)
  private FuelType fuelType;

  @CreatedDate
  private Date created_at = new Date();

  @LastModifiedDate
  private Date updated_at = new Date();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Cars> getCars() {
    return cars;
  }

  public void setCars(List<Cars> cars) {
    this.cars = cars;
  }

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

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
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

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  @PreUpdate
  public void setUpdated_at() {
    this.updated_at = new Date();
  }

}
