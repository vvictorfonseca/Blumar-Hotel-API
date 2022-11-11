package com.projeto.hotel.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String hotelName = "Hotel Blumar";
  
  @Pattern(regexp = "Individual|Acompanhante|Criança", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Escolha o tipo correto: Individual|Acompanhante|Criança")
  private String type;
  
  @Digits(integer = 3, fraction = 0, message = "O número do quarto deve ter 3 dígitos")
  private int number;

  private int price;

  @NotNull(message = "Infome se o quarto está disponível: true|false")
  private boolean available;

  private LocalDateTime registrationDate;

}
