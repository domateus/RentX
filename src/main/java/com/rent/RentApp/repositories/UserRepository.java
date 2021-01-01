package com.rent.RentApp.repositories;

import com.rent.RentApp.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

}
