package com.projeto.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.hotel.model.Room;
import com.projeto.hotel.repository.RoomRepository;

@Service
public class RoomService {

  @Autowired
  private RoomRepository roomDb;

  public ResponseEntity<?> createRoom(Room newRoom) {
    return new ResponseEntity<>(roomDb.save(newRoom), HttpStatus.CREATED);
  }

}
