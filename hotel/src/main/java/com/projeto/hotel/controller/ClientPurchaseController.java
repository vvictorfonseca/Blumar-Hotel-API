package com.projeto.hotel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.hotel.model.ClientPurchase;
import com.projeto.hotel.service.ClientPurchaseService;

@RestController
@RequestMapping("/purchase")
public class ClientPurchaseController {

  @Autowired
  private ClientPurchaseService purchaseService;

  @PostMapping("/create")
  public ResponseEntity<?> newPurchase(@Valid @RequestBody ClientPurchase newPurchase) {
    return purchaseService.newPurchase(newPurchase);
  }

  @GetMapping("/get/{codigo}")
  public ResponseEntity<?> getPurchases(@PathVariable Long codigo) {
    return purchaseService.getClientPurchases(codigo);
  }
  
}
