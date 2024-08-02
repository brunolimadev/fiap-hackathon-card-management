package br.com.fiap.card_management.controller;

import br.com.fiap.card_management.domain.exception.CardLimitException;
import br.com.fiap.card_management.domain.exception.ClientNotFoundException;
import br.com.fiap.card_management.ports.exception.OutputPortException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static org.assertj.core.api.Assertions.assertThat;

class CardControllerAdviceTest {

  @Test
  void shouldHandleForbiddenWithCardLimitException() {

    //Arrange
    var cardControllerAdvice = new CardControllerAdvice();
    var exception = new CardLimitException("Error");

    //Act
    var response = cardControllerAdvice.handleForbiddenWithCardLimitException(exception);

    //Assert
    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.FORBIDDEN);

  }

  @Test
  void shouldHandleInternalServerErrorWithSpringException() {

    //Arrange
    var cardControllerAdvice = new CardControllerAdvice();
    var exception = new HttpMessageNotReadableException("Error");

    //Act
    var response = cardControllerAdvice.handleInternalServerErrorWithSpringException(exception);

    //Assert
    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

  }

  @Test
  void shouldHandleInternalServerErrorWithOutputPortException() {

    //Arrange
    var cardControllerAdvice = new CardControllerAdvice();
    var exception = new OutputPortException("Error");

    //Act
    var response = cardControllerAdvice.handleInternalServerErrorWithOutputPortException(exception);

    //Assert
    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

  }

  @Test
  void shouldHandleInternalServerErrorWithOtherDomainError() {

    //Arrange
    var cardControllerAdvice = new CardControllerAdvice();
    var exception = new ClientNotFoundException("Error");

    //Act
    var response = cardControllerAdvice.handleInternalServerErrorWithOtherDomainError(exception);

    //Assert
    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

  }

}