package com.rent.RentApp.repositories;

import java.util.Optional;

import com.rent.RentApp.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

  Optional<Users> findByEmail(String email);

}
