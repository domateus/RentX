package com.rent.RentApp.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.rent.RentApp.dtos.UserDto;
import com.rent.RentApp.forms.UserForm;
import com.rent.RentApp.models.Users;
import com.rent.RentApp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository repository;

  @GetMapping
  public Page<UserDto> listUsers(
      @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 20) Pageable pagination) {

    Page<Users> users = repository.findAll(pagination);
    return UserDto.convert(users);

  }

  @PostMapping
  public UserDto createUser(@Valid @RequestBody UserForm userForm) {
    Users user = new Users(userForm);
    repository.save(user);

    return UserDto.convert(user);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<UserDto> updateUser(@RequestParam Long id, @Valid @RequestBody UserForm userform) {
    Optional<Users> opUser = repository.findById(id);

    if (opUser.isPresent()) {

      Users user = userform.update(id, repository);

      return ResponseEntity.ok(new UserDto(user));
    }

    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
  }

}
