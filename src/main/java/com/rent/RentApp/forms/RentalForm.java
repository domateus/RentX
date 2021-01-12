package com.rent.RentApp.forms;

import java.util.Date;

import com.rent.RentApp.models.Cars;
import com.rent.RentApp.models.Rentals;
import com.rent.RentApp.models.Users;

public class RentalForm {

  private Long car_id;
  private Long client_id;
  private Date start_date;
  private Date end_date;

  public Rentals update(Rentals rental, Cars car, Users client) {
    rental.setCar(car);
    rental.setClient(client);
    rental.setEnd_date(end_date);
    rental.setStart_date(start_date);

    return rental;
  }

  public Long getCar_id() {
    return car_id;
  }

  public void setCar_id(Long car_id) {
    this.car_id = car_id;
  }

  public Long getClient_id() {
    return client_id;
  }

  public void setClient_id(Long client_id) {
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

}
