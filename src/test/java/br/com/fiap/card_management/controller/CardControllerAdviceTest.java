package br.com.fiap.card_management.controller;

import br.com.fiap.card_management.domain.exception.EntityException;
import br.com.fiap.card_management.ports.exception.OutputPortException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static org.assertj.core.api.Assertions.assertThat;

class CardControllerAdviceTest {

  @Test
  void shouldHandleBadRequestWithDomainException() {

    //Arrange
    var cardControllerAdvice = new CardControllerAdvice();
    var exception = new EntityException("Error");

    //Act
    var response = cardControllerAdvice.handleBadRequestWithDomainException(exception);

    //Assert
    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.BAD_REQUEST);

  }

  @Test
  void shouldHandleBadRequestWithSpringException() {

    //Arrange
    var cardControllerAdvice = new CardControllerAdvice();
    var exception = new HttpMessageNotReadableException("Error");

    //Act
    var response = cardControllerAdvice.handleBadRequestWithSpringException(exception);

    //Assert
    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.BAD_REQUEST);

  }

  @Test
  void shouldHandleUnProcessableEntity() {

    //Arrange
    var cardControllerAdvice = new CardControllerAdvice();
    var exception = new OutputPortException("Error");

    //Act
    var response = cardControllerAdvice.handleUnProcessableEntity(exception);

    //Assert
    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

  }

}