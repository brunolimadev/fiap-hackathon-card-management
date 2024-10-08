package br.com.fiap.card_management.entities;

import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.domain.exception.EntityException;
import org.junit.jupiter.api.Test;

import static br.com.fiap.card_management.utils.MessageEnumUtils.*;
import static br.com.fiap.card_management.utils.ValidateEntityValuesUtils.validateValues;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardEntityTest {

  @Test
  void shouldCreateCardEntity() {

    assertThatNoException().isThrownBy(() -> CardEntity
            .builder()
            .id(1L)
            .cpf("11111111111")
            .limit(1000.0)
            .number("**** **** **** 1234")
            .expiryDate("12/24")
            .cvv("123")
            .build()
    );

  }

  @Test
  void shouldThrowEntityExceptionTryingCreateCardEntityWithEmptyMandatoryValues() {

    var cardEntity = CardEntity
            .builder()
            .id(1L)
            .cpf("")
            .limit(0.0)
            .number("")
            .expiryDate("")
            .cvv("")
            .build();

    assertThatThrownBy(() -> validateValues(cardEntity))
            .isInstanceOf(EntityException.class)
            .hasMessage(MANDATORY_VALUES.getMessage());

  }

  @Test
  void shouldThrowEntityExceptionTryingCreateCardEntityWithNullMandatoryValues() {

    var cardEntity = CardEntity
            .builder()
            .build();

    assertThatThrownBy(() -> validateValues(cardEntity))
            .isInstanceOf(EntityException.class)
            .hasMessage(MANDATORY_VALUES.getMessage());

  }

  @Test
  void shouldThrowEntityExceptionTryingCreateCardEntityWithNegativeLimit() {

    var cardEntity = CardEntity
            .builder()
            .id(1L)
            .cpf("11111111111")
            .limit(-1000.0)
            .number("**** **** **** 1234")
            .expiryDate("12/24")
            .cvv("123")
            .build();

    assertThatThrownBy(() -> validateValues(cardEntity))
            .isInstanceOf(EntityException.class)
            .hasMessage(NEGATIVE_CARD_LIMIT.getMessage());

  }

  @Test
  void shouldThrowEntityExceptionTryingCreateCardEntityWithInvalidCpf() {

    var cardEntity1 = CardEntity
            .builder()
            .id(1L)
            .cpf("1111111111")
            .limit(1000.0)
            .number("**** **** **** 1234")
            .expiryDate("12/24")
            .cvv("123")
            .build();

    var cardEntity2 = CardEntity
            .builder()
            .id(1L)
            .cpf("cpf invalido")
            .limit(1000.0)
            .number("**** **** **** 1234")
            .expiryDate("12/24")
            .cvv("123")
            .build();

    assertThatThrownBy(() -> validateValues(cardEntity1))
            .isInstanceOf(EntityException.class)
            .hasMessage(INVALID_CPF_LENGTH.getMessage());

    assertThatThrownBy(() -> validateValues(cardEntity2))
            .isInstanceOf(EntityException.class)
            .hasMessage(INVALID_CPF_NUMBER.getMessage());

  }

}