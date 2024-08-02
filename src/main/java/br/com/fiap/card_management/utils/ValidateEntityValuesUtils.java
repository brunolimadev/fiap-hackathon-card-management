package br.com.fiap.card_management.utils;

import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.domain.exception.EntityException;
import lombok.experimental.UtilityClass;

import static br.com.fiap.card_management.utils.MessageEnumUtils.*;

@UtilityClass
public class ValidateEntityValuesUtils {

  public static void validateValues (CardEntity cardEntity) throws EntityException {

    validateNullValues(cardEntity);
    validateMandatoryValues(cardEntity);
    validateCardLimit(cardEntity.getLimit());
    validateCpf(cardEntity.getCpf());

  }

  private void validateNullValues(CardEntity cardEntity) {

    if (
            cardEntity.getCpf() == null
                    || cardEntity.getLimit() == null
                    || cardEntity.getNumber() == null
                    || cardEntity.getExpiryDate() == null
                    || cardEntity.getCvv() == null
    ) {

      throw new EntityException(MANDATORY_VALUES.getMessage());

    }


  }

  private void validateMandatoryValues(CardEntity cardEntity) {

    if (
            cardEntity.getCpf().isEmpty()
                    || cardEntity.getNumber().isEmpty()
                    || cardEntity.getExpiryDate().isEmpty()
                    || cardEntity.getCvv().isEmpty()
    ) {

      throw new EntityException(MANDATORY_VALUES.getMessage());

    }

  }

  private void validateCardLimit(Double limit) {

    if (limit < 0) {

      throw new EntityException(NEGATIVE_CARD_LIMIT.getMessage());

    }

  }

  private void validateCpf(String cpf) {

    var regex = "[0-9]+";

    if ((!cpf.matches(regex))) {

      throw new EntityException(INVALID_CPF_NUMBER.getMessage());

    }

    if (cpf.matches(regex) && cpf.length() != 11) {

      throw new EntityException(INVALID_CPF_LENGTH.getMessage());

    }

  }

}