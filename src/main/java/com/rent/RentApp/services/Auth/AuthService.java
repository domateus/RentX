package com.rent.RentApp.services.Auth;

import java.util.Optional;

import com.rent.RentApp.models.Users;
import com.rent.RentApp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Users> user = this.userRepository.findByEmail(username);

    if (user.isPresent())
      return user.get();

    throw new UsernameNotFoundException("Dados inválidos");
  }

}
