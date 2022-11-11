package com.projeto.hotel.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.hotel.ModelResponse.CheckInResponse;
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

  public ResponseEntity<Object> createCheckIn(CheckIn newCheckIn) {
    Optional<Room> roomExist = roomDb.findById(newCheckIn.getRoom().getId());
    
    if (roomExist.isEmpty()) {
      message.setMessage("Esse quarto não existe");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    
    Room room = roomDb.getById(newCheckIn.getRoom().getId());

    if (!newCheckIn.getRoomType().equals(room.getType())) {
      message.setMessage("O Código informado não condiz com o tipo de quarto solicitado. O tipo de quarto desse código é: " + room.getType());
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    
    } else if (roomDb.roomTypeAvailable(room.getType()) == 0) {
      message.setMessage("Não existe quarto deste tipo disponível");
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

    }

    CheckInResponse checkInResponse = new CheckInResponse();
    checkInResponse.setHotelName(room.getHotelName());
    checkInResponse.setNumber(room.getNumber());
    checkInResponse.setClientName(newCheckIn.getClientName());
    checkInResponse.setCheckoutDate(newCheckIn.getCheckoutDate());

    newCheckIn.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
    room.setAvailable(false);

    checkInDb.save(newCheckIn);
    roomDb.save(room);
    return new ResponseEntity<Object>(checkInResponse, HttpStatus.CREATED);

  }

}
