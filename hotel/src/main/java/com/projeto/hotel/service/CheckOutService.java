package com.projeto.hotel.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.hotel.ModelResponse.CheckOutResponse;
import com.projeto.hotel.model.CheckIn;
import com.projeto.hotel.model.CheckOut;
import com.projeto.hotel.model.ClientPurchase;
import com.projeto.hotel.model.ResponseMessage;
import com.projeto.hotel.model.Room;
import com.projeto.hotel.repository.CheckInRepository;
import com.projeto.hotel.repository.CheckOutRepository;
import com.projeto.hotel.repository.ClientPurchaseRepository;
import com.projeto.hotel.repository.RoomRepository;

@Service
public class CheckOutService {

  @Autowired
  private RoomRepository roomDb;

  @Autowired
  private CheckInRepository checkInDb;

  @Autowired
  private CheckOutRepository checkOutDb;

  @Autowired
  private ClientPurchaseRepository purchasesDb;

  @Autowired
  private ResponseMessage message;

  public ResponseEntity<Object> createCheckOut(Long checkInId) {
    Optional<CheckIn> checkInExist = checkInDb.findById(checkInId);
    Optional<CheckOut> checkOutExist = checkOutDb.checkOutExist(checkInId);

    if (!checkInExist.isPresent()) {
      message.setMessage("Esse checkIn não existe");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    } else if (checkOutExist.isPresent()) {
      message.setMessage("Já foi feito o CheckOut neste CheckIn!");
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    List<ClientPurchase> purchases = purchasesDb.clientPurchases(checkInId);
    List<Object> products = purchasesDb.clientProducts(checkInId);
    CheckIn checkIn = checkInDb.getById(checkInId);
    Room room = roomDb.getById(checkIn.getRoom().getId());

    CheckOut checkOut = new CheckOut();
    CheckOutResponse checkOutResponse = new CheckOutResponse();

    purchases.forEach(purchase -> checkOut.setTotalValue(checkOut.getTotalValue() + purchase.getProductPrice()));
    
    LocalDateTime dtToday = LocalDateTime.now(ZoneId.of("UTC"));
    LocalDateTime dtCheckIn = checkIn.getRegistrationDate();

    Long hostingPeriod = ChronoUnit.DAYS.between(dtCheckIn, dtToday);
    int totalValue = checkOut.getTotalValue() + room.getPrice();

    checkOut.setHostingPeriod(hostingPeriod);
    checkOut.setTotalValue(totalValue);
    checkOut.setClientName(checkIn.getClientName());
    checkOut.setRoomNumber(room.getNumber());
    checkOut.setCheckIn(checkIn);

    checkOutResponse.setHostingPeriod(hostingPeriod);
    checkOutResponse.setTotalValue(totalValue);
    checkOutResponse.setClientName(checkIn.getClientName());
    checkOutResponse.setRoomNumber(room.getNumber());
    checkOutResponse.setProducts(products);

    room.setAvailable(true);

    roomDb.save(room);
    checkOutDb.save(checkOut);
    return new ResponseEntity<>(checkOutResponse, HttpStatus.CREATED);

  }
}
