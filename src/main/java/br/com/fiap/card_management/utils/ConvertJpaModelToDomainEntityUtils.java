package br.com.fiap.card_management.utils;

import br.com.fiap.card_management.adapter.repositories.model.CardModel;
import br.com.fiap.card_management.domain.entities.CardEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConvertJpaModelToDomainEntityUtils {

  public static CardEntity convert(CardModel cardModel) {

    return CardEntity
            .builder()
            .id(cardModel.getId())
            .cpf(cardModel.getCpf())
            .limit(cardModel.getLimit())
            .number(cardModel.getNumber())
            .expiryDate(cardModel.getExpiryDate())
            .cvv(cardModel.getCvv())
            .build();

  }

}