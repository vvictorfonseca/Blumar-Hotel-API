package com.projeto.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.hotel.model.CheckIn;
import com.projeto.hotel.model.ResponseMessage;
import com.projeto.hotel.model.Room;
import com.projeto.hotel.repository.CheckInRepository;
import com.projeto.hotel.repository.RoomRepository;

@Service
public class CheckInService {
  
  @Autowired
  private RoomRepository roomDb;

  @Autowired
  private CheckInRepository checkInDb;

  @Autowired
  private ResponseMessage message;

  public ResponseEntity<?> createCheckIn(CheckIn newCheckIn) {
    Room room = roomDb.findByCodigo(newCheckIn.getRoom().getCodigo());

    if(roomDb.roomTypeAvailable(room.getType()) == 0) {
      message.setMessage("Não existe quarto deste tipo disponível");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    
    } else if(roomDb.countByCodigo(room.getCodigo()) == 0) {
      message.setMessage("Esse quarto não existe");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    
    } else 
      room.setAvailable(false);
      return new ResponseEntity<>(checkInDb.save(newCheckIn), HttpStatus.CREATED);
  }

  public ResponseEntity<?> updateRoom(CheckIn updateRoom) {
    Room room = roomDb.findByCodigo(updateRoom.getRoom().getCodigo());
    room.setAvailable(false);
    return new ResponseEntity<>(roomDb.save(room), HttpStatus.OK);
  }
}
