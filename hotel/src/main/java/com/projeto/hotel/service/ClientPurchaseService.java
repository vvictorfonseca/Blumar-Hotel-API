package com.projeto.hotel.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.hotel.ModelResponse.PurchaseResponse;
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
  private ClientPurchaseRepository purchasesDb;

  @Autowired
  private CheckInRepository checkInDb;

  @Autowired
  private ResponseMessage message;

  public ResponseEntity<Object> newPurchase(ClientPurchase newPurchase) {
    Optional<CheckIn> checkIn = checkInDb.findById(newPurchase.getCheckIn().getId());

    if (!checkIn.isPresent()) {
      message.setMessage("Este checkIn não existe");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

    } else if (newPurchase.getProductName().equals("Cerveja")) {
      newPurchase.setProductPrice(5);

    } else if (newPurchase.getProductName().equals("Refrigerante")) {
      newPurchase.setProductPrice(4);

    } else if (newPurchase.getProductName().equals("Água")) {
      newPurchase.setProductPrice(3);
    }

    PurchaseResponse purchaseResponse = new PurchaseResponse();
    purchaseResponse.setProductName(newPurchase.getProductName());
    purchaseResponse.setProductPrice(newPurchase.getProductPrice());

    newPurchase.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
    purchaseDb.save(newPurchase);
    return new ResponseEntity<Object>(purchaseResponse, HttpStatus.CREATED);
  }

  public ResponseEntity<Object> getClientPurchases(Long id) {
    Optional<CheckIn> checkIn = checkInDb.findById(id);
    List<ClientPurchase> purchaseResponse = purchasesDb.clientPurchases(id);

    if (!checkIn.isPresent()) {
      message.setMessage("Este checkIn não existe");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

    } else if (purchaseResponse.isEmpty()) {
      message.setMessage("Não foram registradas compras neste checkIn");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);

  }
}
