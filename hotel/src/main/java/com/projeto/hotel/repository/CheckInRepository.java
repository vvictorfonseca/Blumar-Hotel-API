package com.projeto.hotel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.hotel.model.CheckIn;

@Repository
public interface CheckInRepository extends CrudRepository<CheckIn, Long> {
  
  CheckIn findByCodigo(Long codigo);

  Long countByCodigo(Long codigo);

}
