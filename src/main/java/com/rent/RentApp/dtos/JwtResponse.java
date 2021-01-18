package com.rent.RentApp.dtos;

import java.io.Serializable;

public class JwtResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String jwtToken;

  public String getJwtToken() {
    return jwtToken;
  }

  public JwtResponse(String jwtToken) {
    this.jwtToken = jwtToken;
  }

}
