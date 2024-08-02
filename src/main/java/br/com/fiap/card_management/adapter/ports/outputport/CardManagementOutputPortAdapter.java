package br.com.fiap.card_management.adapter.ports.outputport;

import br.com.fiap.card_management.adapter.repositories.CardRepository;
import br.com.fiap.card_management.adapter.repositories.model.CardModel;
import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.domain.exception.*;
import br.com.fiap.card_management.ports.exception.OutputPortException;
import br.com.fiap.card_management.ports.outputport.CardManagementOutputPort;
import br.com.fiap.card_management.ports.outputport.ClientManagementOutputPort;
import br.com.fiap.card_management.utils.ConvertDomainEntityToJpaModelUtils;
import br.com.fiap.card_management.utils.ConvertJpaModelToDomainEntityUtils;
import com.google.gson.Gson;
import org.json.JSONObject;

import static br.com.fiap.card_management.utils.MessageEnumUtils.*;

public class CardManagementOutputPortAdapter implements CardManagementOutputPort {

  private final CardRepository cardRepository;
  private final ClientManagementOutputPort clientManagementOutputPort;

  public CardManagementOutputPortAdapter(
          CardRepository cardRepository,
          ClientManagementOutputPort clientManagementOutputPort) {

    this.cardRepository = cardRepository;
    this.clientManagementOutputPort = clientManagementOutputPort;

  }

  @Override
  public CardEntity createCard(CardEntity cardEntity) throws OutputPortException {

    try {

      validateExistsClient(clientManagementOutputPort.getClient("CPF NUMBER"), cardEntity);
      validateCardsLimitByClient(cardRepository.countByCpf(cardEntity.getCpf()));
      validateIfExistsCardNumber(cardRepository.findByNumber(cardEntity.getNumber()));

      var card = ConvertDomainEntityToJpaModelUtils.convert(cardEntity);

      return ConvertJpaModelToDomainEntityUtils.convert(
              cardRepository.save(card)
      );

    } catch (DomainException domainException) {

      throw domainException;

    } catch (Exception exception) {

      throw new OutputPortException(ERROR_TO_CREATE_CARD.getMessage());

    }

  }

  @Override
  public CardEntity getCard(String number) throws OutputPortException {

    try {

      return ConvertJpaModelToDomainEntityUtils.convert(cardRepository.findByNumber(number));

    } catch (Exception exception) {

      throw new OutputPortException(ERROR_TO_GET_CARD.getMessage());

    }

  }

  private void validateExistsClient(Object client, CardEntity cardEntity) {

    var clientJsonString = new Gson().toJson(client);
    var clientCpf = new JSONObject(clientJsonString)
            .getString("cpf");

      if (client == null || (!cardEntity.getCpf().contentEquals(clientCpf))) {

        throw new ClientNotFoundException(CLIENT_NOT_FOUND.getMessage());

    }

  }

  private void validateCardsLimitByClient(Long numberOfCards) {

    if (numberOfCards >= 2) {

      throw new CardLimitException(CARD_LIMIT_REACHED.getMessage());

    }

  }

  private void validateIfExistsCardNumber(CardModel cardModel) {

    if (cardModel != null) {

      throw new ExistingCardNumberException(EXISTING_CARD_NUMBER.getMessage());

    }

  }

}