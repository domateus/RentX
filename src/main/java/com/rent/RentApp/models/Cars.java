package com.rent.RentApp.models;

import java.util.Date;

import javax.persistence.*;

import com.rent.RentApp.forms.CarForm;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Cars {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "spec_id")
  private Specs spec;

  private String name;

  private String brand;

  private String daily_value;

  @CreatedDate
  private Date created_at = new Date();

  @LastModifiedDate
  private Date updated_at = new Date();

  public Cars() {
    super();
  }

  public Cars(CarForm form) {
    this.name = form.getName();
    this.brand = form.getBrand();
    this.daily_value = form.getDaily_value();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
