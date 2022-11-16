package com.projeto.hotel.ModelResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckOutResponse {
  
  private String hotelName = "Hotel Blumar";
  private int roomNumber;
  private String clientName;
  private Long hostingPeriod;
  private Object products;
  private int totalValue;

}
