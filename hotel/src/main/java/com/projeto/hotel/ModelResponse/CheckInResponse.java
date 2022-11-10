package com.projeto.hotel.ModelResponse;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckInResponse {
  
  private String hotelName = "Hotel Blumar";
  private int number;
  private String clientName;
  private Date checkoutDate;

}
