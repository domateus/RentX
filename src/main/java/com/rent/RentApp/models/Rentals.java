package com.rent.RentApp.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;

import com.rent.RentApp.forms.RentalForm;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Rentals {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "car_id")
  private Cars car;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private Users client;

  private Date start_date;

  private Date end_date;

  @CreatedDate
  private Date created_at = new Date();

  @LastModifiedDate
  private Date updated_at = new Date();

  protected Rentals() {
    super();
  }

  public Rentals(RentalForm form) {
    this.start_date = form.getStart_date();
    this.end_date = form.getEnd_date();
  }

  public boolean hasCar() {
    return (this.car != null) ? true : false;
  }

  public boolean hasClient() {
    return (this.client != null) ? true : false;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Cars getCar() {
    return car;
  }

  public void setCar(Cars car) {
    this.car = car;
  }

  public Users getClient() {
    return client;
  }

  public void setClient(Users client) {
    this.client = client;
  }

  public Date getStart_date() {
    return start_date;
  }

  public void setStart_date(Date start_date) {
    this.start_date = start_date;
  }

  public Date getEnd_date() {
    return end_date;
  }

  public void setEnd_date(Date end_date) {
    this.end_date = end_date;
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
