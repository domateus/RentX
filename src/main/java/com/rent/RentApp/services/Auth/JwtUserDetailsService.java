package com.rent.RentApp.services.Auth;

import com.rent.RentApp.models.Users;
import com.rent.RentApp.services.Users.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Users user = userService.getByEmail(email);

    if (user.getEmail().equals(email)) {
      return new Users(email, user.getPassword());
    } else {
      throw new UsernameNotFoundException("User not found");
    }

  }

}
