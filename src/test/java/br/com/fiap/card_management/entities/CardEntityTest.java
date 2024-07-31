package br.com.fiap.card_management.entities;

import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.domain.exception.EntityException;
import org.junit.jupiter.api.Test;

import static br.com.fiap.card_management.utils.MessageEnumUtils.ENTITY_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardEntityTest {

  @Test
  void shouldCreateCardEntity() {

    assertThatNoException().isThrownBy(() -> CardEntity
            .builder()
            .id(1L)
            .cpf("1111111111")
            .limit(1000.0)
            .number("**** **** **** 1234")
            .expiryDate("12/24")
            .cvv("123")
            .build()
    );

  }

  @Test
  void shouldThrowEntityExceptionTryingCreateCardEntityWithNullCpf() {

    assertThatThrownBy(() -> CardEntity
            .builder()
            .limit(200.0)
            .number("**** **** **** 1234")
            .build()
    )
            .isInstanceOf(EntityException.class)
            .hasMessage(ENTITY_EXCEPTION.getMessage());

  }

  @Test
  void shouldThrowEntityExceptionTryingCreateCardEntityWithLimitZero() {

    assertThatThrownBy(() -> CardEntity
            .builder()
            .cpf("1111111111")
            .limit(0.0)
            .number("**** **** **** 1234")
            .build()
    )
            .isInstanceOf(EntityException.class)
            .hasMessage(ENTITY_EXCEPTION.getMessage());

  }

}