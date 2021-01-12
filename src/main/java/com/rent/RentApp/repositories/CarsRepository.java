package com.rent.RentApp.repositories;

import com.rent.RentApp.models.Cars;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarsRepository extends JpaRepository<Cars, Long> {

  @Query(value = "SELECT c FROM Cars c LEFT JOIN FETCH c.spec", countQuery = "SELECT count(c) FROM Cars c")
  Page<Cars> findFetchAll(Pageable pagination);

}
