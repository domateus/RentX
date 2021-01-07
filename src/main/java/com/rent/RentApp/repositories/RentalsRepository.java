package com.rent.RentApp.repositories;

import java.util.Optional;

import com.rent.RentApp.models.Rentals;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentalsRepository extends JpaRepository<Rentals, Long> {

  @Query(value = "SELECT v FROM Rentals v LEFT JOIN FETCH v.car LEFT JOIN FETCH v.client", countQuery = "SELECT count(p) FROM Rentals p")
  Page<Rentals> findFetchedRentals(Pageable pagination);

  @Query(value = "SELECT v FROM Rentals v LEFT JOIN FETCH v.car LEFT JOIN FETCH v.client WHERE v.id = :rentalId")
  Optional<Rentals> findFetchById(@Param("rentalId") Long id);
}
