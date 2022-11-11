package com.projeto.hotel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.hotel.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
  
  @Query(value = "SELECT COUNT(type) FROM rooms WHERE available = true AND type = :type", nativeQuery = true)
  Long roomTypeAvailable(String type);

  @Query(value = "SELECT * FROM rooms WHERE id = :id", nativeQuery = true)
  Room getById(Long id);

}
