package com.projeto.hotel.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.hotel.model.CheckIn;

@Repository
public interface CheckInRepository extends CrudRepository<CheckIn, Long> {
  
  Optional<CheckIn> findByCodigo(Long codigo);

  Long countByCodigo(Long codigo);

}
