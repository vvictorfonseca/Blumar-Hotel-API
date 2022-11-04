package com.projeto.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.hotel.model.CheckIn;
import com.projeto.hotel.model.ClientPurchase;
import com.projeto.hotel.model.ResponseMessage;
import com.projeto.hotel.repository.CheckInRepository;
import com.projeto.hotel.repository.ClientPurchaseRepository;

@Service
public class ClientPurchaseService {

  @Autowired
  private ClientPurchaseRepository purchaseDb;

  @Autowired
  private CheckInRepository checkInDb;

  @Autowired
  private ResponseMessage message;

  public ResponseEntity<?> newPurchase(ClientPurchase newPurchase) {
    CheckIn checkIn = checkInDb.findByCodigo(newPurchase.getCheckIn().getCodigo());

    if (checkInDb.countByCodigo(checkIn.getCodigo()) == 0) {
      message.setMessage("Este checkIn não existe");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

    } else if (newPurchase.getProductName().equals("Cerveja") || newPurchase.getProductName().equals("cerveja")) {
      newPurchase.setProductPrice(5);
    
    } else if (newPurchase.getProductName().equals("Refrigerante") || newPurchase.getProductName().equals("Refrigerante")) {
      newPurchase.setProductPrice(4);
    
    } else if (newPurchase.getProductName().equals("Água") || newPurchase.getProductName().equals("água")) {
      newPurchase.setProductPrice(3);
    }

    return new ResponseEntity<>(purchaseDb.save(newPurchase), HttpStatus.CREATED);
  
  }

  public ResponseEntity<?> getClientPurchases(Long codigo) {
    CheckIn checkIn = checkInDb.findByCodigo(codigo);

    if(checkIn.equals(null)) {
      message.setMessage("Este checkIn não existe");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    
    } else {
      return new ResponseEntity<>(purchaseDb.clientPurchases(codigo), HttpStatus.OK);
    }
  }
}
