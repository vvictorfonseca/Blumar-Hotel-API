package com.projeto.hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.hotel.model.CheckIn;

@Repository
public interface CheckInRepository extends CrudRepository<CheckIn, Long> {
  
  Optional<CheckIn> findById(Long id);

  @Query(value = "Select * from checkins WHERE id = :id", nativeQuery = true)
  CheckIn getById(Long id);

}
