package br.com.fiap.card_management.outputport;

import br.com.fiap.card_management.adapter.ports.outputport.CardManagementOutputPortAdapter;
import br.com.fiap.card_management.adapter.repositories.CardRepository;
import br.com.fiap.card_management.adapter.repositories.model.CardModel;
import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.domain.exception.EntityException;
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
import java.util.Optional;

import static br.com.fiap.card_management.utils.MessageEnumUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
  void shouldThrowOutputPortExceptionTryingCreateCardNoClientFound() {

    //Arrange
    var cardEntity = CardEntityMock.get();
    var client = new HashMap<>(ClientMock.get());

    client.replace("cpf", "22222222");

    when(clientManagementOutputPort.getClient(anyString())).thenReturn(client);

    //Act & Assert
    assertThatThrownBy(() -> cardManagementOutputPort.createCard(cardEntity))
            .isInstanceOf(OutputPortException.class);

  }

  @Test
  void shouldThrowOutputPortExceptionTryingCreateCardErrorToSaveCard() {

    //Arrange
    var cardEntity = CardEntityMock.get();

    when(cardRepository.save(any(CardModel.class))).thenThrow(OutputPortException.class);

    //Act & Assert
    assertThatThrownBy(() -> cardManagementOutputPort.createCard(cardEntity))
            .isInstanceOf(OutputPortException.class)
            .hasMessage(CARD_MANAGEMENT_CREATE_CARD_OUTPUT_PORT_EXCEPTION.getMessage());

  }

  @Test
  void shouldGetCardWithSuccess() {

    //Arrange
    var cardModel = CardModelMock.get();
    var cardEntity = CardEntityMock.get();
    var cardEntityId = cardEntity.getId();
    var client = new Object();

    when(cardRepository.findById(anyLong())).thenReturn(Optional.of(cardModel));

    when(clientManagementOutputPort.getClient(anyString())).thenReturn(client);

    //Act
    var response = cardManagementOutputPort.getCard("12345678");

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(CardEntity.class);

    assertThat(response.getId()).isEqualTo(cardEntityId);
    verify(cardRepository, times(1)).findById(anyLong());

  }

  @Test
  void shouldThrowOutputPortExceptionTryingGetCard() {

    //Arrange
    var client = new Object();
    var cardEntity = CardEntityMock.get();
    var cardEntityId = cardEntity.getId();

    when(cardRepository.findById(anyLong())).thenThrow(RuntimeException.class);

    when(clientManagementOutputPort.getClient(anyString())).thenReturn(client);

    //Act & Assert
    assertThatThrownBy(() -> cardManagementOutputPort.getCard("12345678"))
            .isInstanceOf(OutputPortException.class)
            .hasMessage(CARD_MANAGEMENT_GET_CARD_OUTPUT_PORT_EXCEPTION.getMessage());
    verify(cardRepository, times(1)).findById(anyLong());

  }

}