package com.projeto.hotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "checkouts")
@Getter
@Setter
public class CheckOut {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "checkInId", referencedColumnName = "id")
  private CheckIn checkIn;

  private String hotelName = "Hotel Blumar";

  @Digits(integer = 3, fraction = 0, message = "O número do quarto deve ter 3 dígitos")
  private int roomNumber;

  @NotEmpty(message = "Informe seu nome")
  @Length(min = 3, max = 30, message = "Informe um nome válido: mín. 3 caracteres, máx. 30 caracteres")
  private String clientName;

  private Long hostingPeriod;

  private int totalValue;
}
