package br.com.fiap.card_management.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CardEntity {

  @Hidden
  private Long id;
  @JsonProperty("cpf")
  private String cpf;
  @JsonProperty("limite")
  private Double limit;
  @JsonProperty("numero")
  private String number;
  @JsonProperty("data_validade")
  private String expiryDate;
  @JsonProperty("cvv")
  private String cvv;

}