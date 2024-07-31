package br.com.fiap.card_management.mock;

import br.com.fiap.card_management.adapter.repositories.model.CardModel;

public class CardModelMock {

  public static CardModel get() {

    return CardModel
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