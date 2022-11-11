package com.projeto.hotel.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "checkins")
@Getter
@Setter
public class CheckIn {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "roomId", referencedColumnName = "id")
  private Room room;

  @Pattern(regexp = "Individual|Acompanhante|Criança", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Escolha o tipo correto: Individual|Acompanhante|Criança")
  private String roomType;

  @NotEmpty(message = "Informe seu nome")
  @Length(min = 3, max = 30, message = "Informe um nome válido: mín. 3 caracteres, máx. 30 caracteres")
  private String clientName;

  @Future(message = "Insira uma data válida! yyyy/MM/DD")
  private Date checkoutDate;

  private LocalDateTime registrationDate;
}
