package com.rent.RentApp.forms;

import java.util.UUID;

import com.rent.RentApp.models.Users;
import com.rent.RentApp.repositories.UserRepository;

public class UserForm {

  private String name;
  private String email;
  private String password;

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
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Users update(Long id, UserRepository repository) {
    Users user = repository.getOne(id);

    user.setName(this.name);
    user.setEmail(this.email);
    user.setPassword(this.password);

    return user;
  }

}
