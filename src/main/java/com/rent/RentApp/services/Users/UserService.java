package com.rent.RentApp.services.Users;

import java.util.Optional;

import com.rent.RentApp.dtos.UserDto;
import com.rent.RentApp.forms.UserForm;
import com.rent.RentApp.models.Users;
import com.rent.RentApp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public Page<UserDto> findPages(Pageable pagination) {
    Page<Users> users = this.userRepository.findAll(pagination);

    return UserDto.convert(users);
  }

  public UserDto create(UserForm form) {
    try {
      Users user = new Users(form);
      this.userRepository.save(user);

      return new UserDto(user);

    } catch (DataIntegrityViolationException exc) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");

    }
  }

  public UserDto update(UserForm form, Long id) {
    Optional<Users> opUser = this.userRepository.findById(id);

    if (opUser.isPresent()) {

      Users user = form.update(opUser.get());

      return new UserDto(user);
    }

    return null;
  }

  public void delete(Long id) {
    this.userRepository.deleteById(id);
  }

  public Users getByEmail(String email) {
    return this.userRepository.findByEmail(email).get();
  }

}
