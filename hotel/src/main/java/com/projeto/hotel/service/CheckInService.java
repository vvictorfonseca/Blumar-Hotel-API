package com.projeto.hotel.service;

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
    Room room = roomDb.findByCodigo(newCheckIn.getRoom().getCodigo());
    String type = room.getType();

    if (roomDb.roomTypeAvailable(room.getType()) == 0) {
      message.setMessage("Não existe quarto deste tipo disponível");
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

    } else if (!room.isAvailable()) {
      message.setMessage("Este quarto não está disponível");
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

    } else if (room.equals(null)) {
      message.setMessage("Esse quarto não existe");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

    } else if (!newCheckIn.getRoomType().equals(type)) {
      message.setMessage("O Código informado não condiz com o tipo de quarto solicitado. O tipo de quarto desse código é: " + type);
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    CheckInResponse checkInResponse = new CheckInResponse();
    checkInResponse.setHotelName(room.getHotelName());
    checkInResponse.setNumber(room.getNumber());
    checkInResponse.setClientName(newCheckIn.getClientName());
    checkInResponse.setCheckoutDate(newCheckIn.getCheckoutDate());

    room.setAvailable(false);

    checkInDb.save(newCheckIn);
    roomDb.save(room);
    return new ResponseEntity<Object>(checkInResponse, HttpStatus.CREATED);

  }

}
