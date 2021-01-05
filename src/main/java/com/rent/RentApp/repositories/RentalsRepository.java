package com.rent.RentApp.repositories;

import com.rent.RentApp.models.Rentals;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalsRepository extends JpaRepository<Rentals, Long> {

}
