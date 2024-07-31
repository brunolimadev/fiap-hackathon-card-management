package br.com.fiap.card_management.mock;

import br.com.fiap.card_management.domain.entities.CardEntity;

public class CardEntityMock {

  public static CardEntity get() {

    return CardEntity
            .builder()
            .id(1L)
            .cpf("1111111111")
            .limit(1000.0)
            .number("**** **** **** 1234")
            .expiryDate("12/24")
            .cvv("123")
            .build();

  }

}