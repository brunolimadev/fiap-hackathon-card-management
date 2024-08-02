package br.com.fiap.card_management.entities;

import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.domain.exception.EntityException;
import org.junit.jupiter.api.Test;

import static br.com.fiap.card_management.utils.MessageEnumUtils.ENTITY_EXCEPTION;
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
  void shouldThrowEntityExceptionTryingCreateCardEntityWithNullCpf() {

    var cardEntity = CardEntity
            .builder()
            .limit(200.0)
            .number("**** **** **** 1234")
            .build();

    assertThatThrownBy(() -> validateValues(cardEntity))
            .isInstanceOf(EntityException.class)
            .hasMessage(ENTITY_EXCEPTION.getMessage());

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
            .hasMessage(ENTITY_EXCEPTION.getMessage());

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
            .hasMessage(ENTITY_EXCEPTION.getMessage());

    assertThatThrownBy(() -> validateValues(cardEntity2))
            .isInstanceOf(EntityException.class)
            .hasMessage(ENTITY_EXCEPTION.getMessage());

  }

}