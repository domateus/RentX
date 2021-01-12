package com.rent.RentApp.dtos;

import java.util.Date;

import com.rent.RentApp.models.Rentals;

import org.springframework.data.domain.Page;

public class RentalDto {

  private String car_id;
  private String client_id;
  private Date start_date;
  private Date end_date;
  private Long id;

  public RentalDto(Rentals rental) {
    if (rental.hasCar()) {
      this.car_id = String.valueOf(rental.getCar().getId());
    } else {
      this.car_id = "null";
    }
    if (rental.hasClient()) {
      this.client_id = String.valueOf(rental.getClient().getId());
    } else {
      this.client_id = "null";
    }
    this.start_date = rental.getStart_date();
    this.end_date = rental.getEnd_date();
    this.id = rental.getId();
  }

  public static Page<RentalDto> convert(Page<Rentals> rentals) {
    return rentals.map(RentalDto::new);
  }

  public String getCar_id() {
    return car_id;
  }

  public void setCar_id(String car_id) {
    this.car_id = car_id;
  }

  public String getClient_id() {
    return client_id;
  }

  public void setClient_id(String client_id) {
    this.client_id = client_id;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
