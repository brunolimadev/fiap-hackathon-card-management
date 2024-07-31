package br.com.fiap.card_management.ports.outputport;

import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.ports.exception.OutputPortException;

public interface CardManagementOutputPort {

  CardEntity createCard(CardEntity cardEntity) throws OutputPortException;

  CardEntity getCard(String number) throws OutputPortException;
  
}