package com.projeto.hotel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.hotel.service.CheckOutService;

@RestController
@RequestMapping("checkOut")
public class CheckOutController {
  
  @Autowired
  private CheckOutService checkOutService;

  @PostMapping("/{id}")
  public ResponseEntity<?> createCheckOut(@Valid @PathVariable Long id) {
    return checkOutService.createCheckOut(id);
  }

}
