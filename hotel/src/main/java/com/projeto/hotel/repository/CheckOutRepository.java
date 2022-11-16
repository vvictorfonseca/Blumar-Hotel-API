package com.projeto.hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.projeto.hotel.model.CheckOut;

public interface CheckOutRepository extends CrudRepository<CheckOut, Long> {
  
  @Query(value = "Select * from checkouts WHERE check_in_id = :id", nativeQuery = true)
  Optional<CheckOut> checkOutExist(Long id);

}
