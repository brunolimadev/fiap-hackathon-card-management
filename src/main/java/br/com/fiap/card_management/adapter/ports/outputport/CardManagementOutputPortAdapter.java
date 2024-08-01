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

      throw new OutputPortException(CARD_MANAGEMENT_CREATE_CARD_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

  @Override
  public CardEntity getCard(String number) throws OutputPortException {

    try {

      return ConvertJpaModelToDomainEntityUtils.convert(cardRepository.findById(Long.parseLong(number)).orElseThrow());

    } catch (Exception exception) {

      throw new OutputPortException(CARD_MANAGEMENT_GET_CARD_OUTPUT_PORT_EXCEPTION.getMessage());

    }

  }

  private void validateExistsClient(Object client, CardEntity cardEntity) {

    var clientJsonString = new Gson().toJson(client);
    var clientCpf = new JSONObject(clientJsonString)
            .getString("cpf");

      if (client == null || (!cardEntity.getCpf().contentEquals(clientCpf))) {

        throw new ClientNotFoundException("Cliente nao encontrado");

    }

  }

  private void validateCardsLimitByClient(Long numberOfCards) {

    if (numberOfCards >= 2) {

      throw new CardLimitException("O cliente atingiu a quantidade máxima de cartoes permitida");

    }

  }

  private void validateIfExistsCardNumber(CardModel cardModel) {

    if (cardModel != null) {

      throw new ExistingCardNumberException("Ja existe um cartao registrado com o numero informado");

    }

  }

}