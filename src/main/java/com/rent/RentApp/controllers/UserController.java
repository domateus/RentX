package com.rent.RentApp.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.rent.RentApp.dtos.UserDto;
import com.rent.RentApp.forms.UserForm;
import com.rent.RentApp.models.Users;
import com.rent.RentApp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository repository;

  /*
   * @GetMapping public List<UserDto> listUsers() {
   * 
   * List<Users> users = repository.findAll();
   * 
   * return UserDto.convert(users); }
   */

  @GetMapping
  public List<Users> listUsers() {
    return repository.findAll();
  }

  @PostMapping
  public UserDto createUser(@RequestBody UserForm userForm) {
    Users user = new Users(userForm);
    repository.save(user);

    return UserDto.convert(user);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<UserDto> updateUser(@RequestParam Long id, @RequestBody UserForm userform) {
    Optional<Users> opUser = repository.findById(id);

    if (opUser.isPresent()) {

      Users user = userform.update(id, repository);

      return ResponseEntity.ok(new UserDto(user));
    }

    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
  }

}
