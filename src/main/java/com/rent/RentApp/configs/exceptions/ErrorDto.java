package com.rent.RentApp.configs.exceptions;

public class ErrorDto {

  private String field;
  private String error;

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public ErrorDto(String field, String error) {
    this.field = field;
    this.error = error;
  }

}
