package com.rent.RentApp.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.rent.RentApp.models.Users;

public class UserDto {

  private String name;
  private byte[] image;

  public UserDto(Users user) {
    this.name = user.getName();
    this.image = user.getImage();
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

  public static List<UserDto> convert(List<Users> users) {
    return users.stream().map(UserDto::new).collect(Collectors.toList());
  }

  public static UserDto convert(Users user) {
    return new UserDto(user);
  }

}
