package br.com.fiap.card_management.utils;

import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.domain.exception.EntityException;
import lombok.experimental.UtilityClass;

import static br.com.fiap.card_management.utils.MessageEnumUtils.ENTITY_EXCEPTION;

@UtilityClass
public class ValidateEntityValuesUtils {

  public static void validateValues (
          CardEntity cardEntity
  ) throws EntityException {

    if (
            cardEntity.getCpf() == null
                    || cardEntity.getLimit() == null
                    || cardEntity.getNumber() == null
                    || cardEntity.getExpiryDate() == null
                    || cardEntity.getCvv() == null
    ) {

      throw new EntityException(ENTITY_EXCEPTION.getMessage());

    }

    if (
            cardEntity.getCpf().isEmpty()
                    || cardEntity.getLimit() < 0
                    || cardEntity.getNumber().isEmpty()
    ) {

      throw new EntityException(ENTITY_EXCEPTION.getMessage());

    }

  }

}