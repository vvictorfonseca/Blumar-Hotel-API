package com.projeto.hotel.model;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {
  
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
