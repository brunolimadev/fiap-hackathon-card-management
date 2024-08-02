package br.com.fiap.card_management.outputport;

import br.com.fiap.card_management.adapter.ports.outputport.CardManagementOutputPortAdapter;
import br.com.fiap.card_management.adapter.repositories.CardRepository;
import br.com.fiap.card_management.adapter.repositories.model.CardModel;
import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.domain.exception.CardLimitException;
import br.com.fiap.card_management.domain.exception.ClientNotFoundException;
import br.com.fiap.card_management.domain.exception.EntityException;
import br.com.fiap.card_management.domain.exception.ExistingCardNumberException;
import br.com.fiap.card_management.mock.CardEntityMock;
import br.com.fiap.card_management.mock.CardModelMock;
import br.com.fiap.card_management.mock.ClientMock;
import br.com.fiap.card_management.ports.exception.OutputPortException;
import br.com.fiap.card_management.ports.outputport.CardManagementOutputPort;
import br.com.fiap.card_management.ports.outputport.ClientManagementOutputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static br.com.fiap.card_management.utils.MessageEnumUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CardManagementOutputPortAdapterTest {

  @Mock
  private CardRepository cardRepository;

  @Mock
  private ClientManagementOutputPort clientManagementOutputPort;

  private CardManagementOutputPort cardManagementOutputPort;
  private AutoCloseable openMocks;

  @BeforeEach
  void setup() {

    openMocks = MockitoAnnotations.openMocks(this);
    cardManagementOutputPort = new CardManagementOutputPortAdapter(cardRepository, clientManagementOutputPort);

  }

  @AfterEach
  void tearDown() throws Exception {

    openMocks.close();

  }

  @Test
  void shouldCreateCardWithSuccess() {

    //Arrange
    var cardModel = CardModelMock.get();
    var cardEntityId = 1L;
    var client = ClientMock.get();

    when(cardRepository.save(any(CardModel.class))).thenReturn(cardModel);

    when(clientManagementOutputPort.getClient(anyString())).thenReturn(client);

    //Act
    var response = cardManagementOutputPort.createCard(CardEntityMock.get());

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(CardEntity.class);

    assertThat(response.getId()).isEqualTo(cardEntityId);

  }

  @Test
  void shouldThrowEntityExceptionTryingCreateCard() {

    //Arrange
    var cardEntity = CardEntityMock.get();
    var client = ClientMock.get();

    when(cardRepository.save(any(CardModel.class))).thenThrow(EntityException.class);

    when(clientManagementOutputPort.getClient(anyString())).thenReturn(client);

    //Act & Assert
    assertThatThrownBy(() -> cardManagementOutputPort.createCard(cardEntity))
            .isInstanceOf(EntityException.class);

  }

  @Test
  void shouldThrowClientNotFoundExceptionTryingCreateCard() {

    //Arrange
    var cardEntity = CardEntityMock.get();
    var client = new HashMap<>(ClientMock.get());

    client.replace("cpf", "22222222222");

    when(clientManagementOutputPort.getClient(anyString())).thenReturn(client);

    //Act & Assert
    assertThatThrownBy(() -> cardManagementOutputPort.createCard(cardEntity))
            .isInstanceOf(ClientNotFoundException.class);

  }

  @Test
  void shouldThrowCardLimitExceptionTryingCreateCard() {

    //Arrange
    var cardEntity = CardEntityMock.get();
    var client = new HashMap<>(ClientMock.get());

    when(cardRepository.countByCpf(anyString())).thenReturn(2L);

    when(clientManagementOutputPort.getClient(anyString())).thenReturn(client);

    //Act & Assert
    assertThatThrownBy(() -> cardManagementOutputPort.createCard(cardEntity))
            .isInstanceOf(CardLimitException.class);

  }

  @Test
  void shouldThrowExistingCardNumberExceptionTryingCreateCard() {

    //Arrange
    var cardEntity = CardEntityMock.get();
    var cartModel = CardModelMock.get();
    var client = new HashMap<>(ClientMock.get());

    when(cardRepository.findByNumber(anyString())).thenReturn(cartModel);

    when(clientManagementOutputPort.getClient(anyString())).thenReturn(client);

    //Act & Assert
    assertThatThrownBy(() -> cardManagementOutputPort.createCard(cardEntity))
            .isInstanceOf(ExistingCardNumberException.class);

  }

  @Test
  void shouldThrowOutputPortExceptionTryingCreateCardErrorToSaveCard() {

    //Arrange
    var cardEntity = CardEntityMock.get();

    when(cardRepository.save(any(CardModel.class))).thenThrow(OutputPortException.class);

    //Act & Assert
    assertThatThrownBy(() -> cardManagementOutputPort.createCard(cardEntity))
            .isInstanceOf(OutputPortException.class)
            .hasMessage(ERROR_TO_CREATE_CARD.getMessage());

  }

  @Test
  void shouldGetCardWithSuccess() {

    //Arrange
    var cardModel = CardModelMock.get();
    var cardNumber = CardEntityMock.get().getNumber();

    when(cardRepository.findByNumber(anyString())).thenReturn(cardModel);

    //Act
    var response = cardManagementOutputPort.getCard(cardNumber);

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(CardEntity.class);

    assertThat(response.getId()).isEqualTo(cardModel.getId());
    verify(cardRepository, times(1)).findByNumber(anyString());

  }

  @Test
  void shouldThrowOutputPortExceptionTryingGetCard() {

    //Arrange
    var cardNumber = CardEntityMock.get().getNumber();

    when(cardRepository.findByNumber(anyString())).thenThrow(RuntimeException.class);

    //Act & Assert
    assertThatThrownBy(() -> cardManagementOutputPort.getCard(cardNumber))
            .isInstanceOf(OutputPortException.class)
            .hasMessage(ERROR_TO_GET_CARD.getMessage());
    verify(cardRepository, times(1)).findByNumber(anyString());

  }

}