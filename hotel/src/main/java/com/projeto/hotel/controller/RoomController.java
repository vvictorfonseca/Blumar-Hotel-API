package com.projeto.hotel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.hotel.model.Room;
//import com.projeto.hotel.repository.RoomRepository;
import com.projeto.hotel.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
  
  // @Autowired
  // private RoomRepository db;

  @Autowired
  private RoomService roomService;

  @PostMapping("/create")
  public ResponseEntity<?> createRoom(@Valid @RequestBody Room createRoom) {
    return roomService.createRoom(createRoom);
  }

}
