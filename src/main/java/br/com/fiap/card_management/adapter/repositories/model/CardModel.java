package br.com.fiap.card_management.adapter.repositories.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "card")
public class CardModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "card_limit")
  private Double limit;

  @Column(name = "number")
  private String number;

  @Column(name = "expiry_date")
  private String expiryDate;

  @Column(name = "cvv")
  private String cvv;

}