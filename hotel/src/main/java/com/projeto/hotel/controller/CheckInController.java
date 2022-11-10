package com.projeto.hotel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.hotel.model.CheckIn;
import com.projeto.hotel.service.CheckInService;

@RestController
@RequestMapping("/checkIn")
public class CheckInController {

  @Autowired
  private CheckInService checkInService;

  @PostMapping("/create")
  public ResponseEntity<?> createCheckIn(@Valid @RequestBody CheckIn newCheckIn) {
    return checkInService.createCheckIn(newCheckIn);
  }
}
