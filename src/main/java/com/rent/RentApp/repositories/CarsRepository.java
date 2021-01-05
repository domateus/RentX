package com.rent.RentApp.repositories;

import com.rent.RentApp.models.Cars;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Cars, Long> {

}
