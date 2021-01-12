package com.rent.RentApp.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.rent.RentApp.models.Users;

import org.springframework.data.domain.Page;

public class UserDto {

  private Long id;
  private String name;
  private byte[] image;
  private String email;
  private boolean admin;

  public UserDto(Users user) {
    this.name = user.getName();
    this.image = user.getImage();
    this.email = user.getEmail();
    this.admin = user.isAdmin();
    this.id = user.getId();
  }

  public static List<UserDto> convert(List<Users> users) {
    return users.stream().map(UserDto::new).collect(Collectors.toList());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public static UserDto convert(Users user) {
    return new UserDto(user);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public static Page<UserDto> convert(Page<Users> users) {
    return users.map(UserDto::new);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
