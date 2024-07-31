package br.com.fiap.card_management.utils;

import br.com.fiap.card_management.adapter.repositories.model.CardModel;
import br.com.fiap.card_management.domain.entities.CardEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConvertDomainEntityToJpaModelUtils {

  public static CardModel convert(CardEntity cardEntity) {

    return CardModel
            .builder()
            .id(cardEntity.getId())
            .cpf(cardEntity.getCpf())
            .limit(cardEntity.getLimit())
            .number(cardEntity.getNumber())
            .expiryDate(cardEntity.getExpiryDate())
            .cvv(cardEntity.getCvv())
            .build();

  }

}
