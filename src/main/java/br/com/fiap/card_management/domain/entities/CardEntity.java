package br.com.fiap.card_management.domain.entities;

import br.com.fiap.card_management.domain.exception.EntityException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Builder;
import lombok.Getter;

import static br.com.fiap.card_management.utils.MessageEnumUtils.ENTITY_EXCEPTION;

@Getter
@Builder
public class CardEntity {

  @Hidden
  private Long id;
  private String cpf;
  private Double limit;
  private String number;
  private String expiryDate;
  private String cvv;

  public CardEntity(
          Long id, String cpf, Double limit, String number,
          String expiryDate, String cvv
  ) {

    validateValues(cpf, limit, number, expiryDate, cvv);

    this.id = id;
    this.cpf = cpf;
    this.limit = limit;
    this.number = number;
    this.expiryDate = expiryDate;
    this.cvv = cvv;

  }

  private void validateValues (
          String cpf, Double limit, String number,
          String expiryDate, String cvv
  ) throws EntityException {

    if (
            cpf == null || limit == null || number == null
                    || expiryDate == null || cvv == null
    ) {

      throw new EntityException(ENTITY_EXCEPTION.getMessage());

    }

    if (
            cpf.isEmpty() || limit <= 0 || number.isEmpty()
    ) {

      throw new EntityException(ENTITY_EXCEPTION.getMessage());

    }

  }


}