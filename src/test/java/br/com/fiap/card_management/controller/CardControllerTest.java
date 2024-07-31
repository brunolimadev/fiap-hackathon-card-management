package br.com.fiap.card_management.controller;

import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.mock.CardEntityMock;
import br.com.fiap.card_management.ports.outputport.CardManagementOutputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = CardController.class)
@ExtendWith(MockitoExtension.class)
class CardControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private CardManagementOutputPort cardManagementOutputPort;

  private CardEntity cardEntity;

  @BeforeEach
  public void init () {

    cardEntity = CardEntityMock.get();

  }

  @Test
  void shouldCreateCardWithSuccess() throws Exception {

    //Arrange
    given(cardManagementOutputPort.createCard(any())).willAnswer(invocation -> invocation.getArgument(0));

    //Act
    var response = mockMvc.perform(post("http://localhost:8083/cartao")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(cardEntity)));

    //Assert
    response
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.cpf", CoreMatchers.is(cardEntity.getCpf())));

  }

  @Test
  void shouldGetCardWithSuccess() throws Exception {

    //Arrange
    var cardId = cardEntity.getId();
    when(cardManagementOutputPort.getCard("")).thenReturn(cardEntity);

    //Act
    var response = mockMvc.perform(get("http://localhost:8083/cartao/12345678910")
            .param("numero", String.valueOf(cardId))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(cardEntity)));

    //Assert
    response
            .andExpect(MockMvcResultMatchers.status().isOk());

  }

}