package com.rent.RentApp.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.rent.RentApp.models.Users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserForm {

  @NotBlank
  private String name;
  @NotBlank
  @Email(regexp = "^[a-zA-Z0-9._]+@[a-z]+(\\.[a-z]{2,6}){1,3}")
  private String email;
  @NotBlank
  @Size(min = 6, max = 30)
  private String password;

  private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(8);

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return bcrypt.encode(password);
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Users update(Users user) {

    user.setName(this.name);
    user.setEmail(this.email);
    user.setPassword(this.password);

    return user;
  }

}
