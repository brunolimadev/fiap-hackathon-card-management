package br.com.fiap.card_management.outputport;

import br.com.fiap.card_management.adapter.ports.outputport.ClientManagementOutputPortAdapter;
import br.com.fiap.card_management.ports.outputport.ClientManagementOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClientManagementOutputPortAdapterTest {

  private ClientManagementOutputPort clientManagementOutputPort;

  @BeforeEach
  void setup() {

    clientManagementOutputPort = new ClientManagementOutputPortAdapter();

  }

  @Test
  void shouldGetClientWithSuccess() {

    //Arrange
    var cpf = "11111111111";

    //Act
    var response = clientManagementOutputPort.getClient(cpf);

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(Object.class);

  }

}