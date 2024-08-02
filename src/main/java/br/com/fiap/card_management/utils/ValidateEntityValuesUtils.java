package br.com.fiap.card_management.utils;

import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.domain.exception.EntityException;
import lombok.experimental.UtilityClass;

import static br.com.fiap.card_management.utils.MessageEnumUtils.ENTITY_EXCEPTION;

@UtilityClass
public class ValidateEntityValuesUtils {

  public static void validateValues (CardEntity cardEntity) throws EntityException {

    validateNullValues(cardEntity);
    validateMandatoryValues(cardEntity);
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

      throw new EntityException(ENTITY_EXCEPTION.getMessage());

    }


  }

  private void validateMandatoryValues(CardEntity cardEntity) {

    if (
            cardEntity.getCpf().isEmpty()
                    || cardEntity.getLimit() < 0
                    || cardEntity.getNumber().isEmpty()
    ) {

      throw new EntityException(ENTITY_EXCEPTION.getMessage());

    }

  }

  private void validateCpf(String cpf) {

    if ((!cpf.matches("[0-9]+"))) {

      throw new EntityException(ENTITY_EXCEPTION.getMessage());

    }

    if (cpf.matches("[0-9]+") && cpf.length() != 11) {

      throw new EntityException(ENTITY_EXCEPTION.getMessage());

    }

  }

}