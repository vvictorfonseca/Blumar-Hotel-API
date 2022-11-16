package com.projeto.hotel.repository;

import org.springframework.data.repository.CrudRepository;

import com.projeto.hotel.model.CheckOut;

public interface CheckOutRepository extends CrudRepository<CheckOut, Long> {
  
}
