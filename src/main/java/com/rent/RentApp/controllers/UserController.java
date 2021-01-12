package com.rent.RentApp.controllers;

import javax.validation.Valid;

import com.rent.RentApp.dtos.UserDto;
import com.rent.RentApp.forms.UserForm;
import com.rent.RentApp.repositories.UserRepository;
import com.rent.RentApp.services.Users.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository repository;

  @GetMapping
  public Page<UserDto> listUsers(
      @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 20) Pageable pagination) {
    Page<UserDto> users = this.userService.findPages(pagination);

    return users;
  }

  @PostMapping
  public UserDto createUser(@Valid @RequestBody UserForm form) {
    UserDto user = this.userService.create(form);

    return user;
  }

  @PutMapping("/{id}")
  @Transactional
  public UserDto updateUser(@PathVariable Long id, @Valid @RequestBody UserForm form) {
    UserDto user = this.userService.update(form, id);

    return user;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable Long id) {
    this.userService.delete(id);
  }

}
