package com.projeto.hotel.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

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

    if(newRoom.getType().equals("Individual")) {
      newRoom.setPrice(30);
    
    } else if(newRoom.getType().equals("Acompanhante")) {
      newRoom.setPrice(50);
    
    } else if(newRoom.getType().equals("Criança")) {
      newRoom.setPrice(80);
    }

    newRoom.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
    return new ResponseEntity<>(roomDb.save(newRoom), HttpStatus.CREATED);
  }

}
