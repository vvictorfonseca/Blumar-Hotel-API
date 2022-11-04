package com.projeto.hotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientPurchases")
@Setter
@Getter
public class ClientPurchase {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  @OneToOne
  @JoinColumn(name = "codigo_checkIn", referencedColumnName = "codigo")
  private CheckIn checkIn;

  @NotEmpty(message = "Preencha o nome do produto")
  @Pattern(regexp = "Água|Refrigerante|Cerveja", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Escolha um produto válido: Água|Refrigerante|Cerveja")
  private String productName;

  private int productPrice;

}
