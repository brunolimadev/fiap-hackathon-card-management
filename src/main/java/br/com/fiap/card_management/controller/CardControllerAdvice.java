package br.com.fiap.card_management.controller;

import br.com.fiap.card_management.domain.entities.MessageEntity;
import br.com.fiap.card_management.domain.exception.CardLimitException;
import br.com.fiap.card_management.domain.exception.ClientNotFoundException;
import br.com.fiap.card_management.domain.exception.EntityException;
import br.com.fiap.card_management.domain.exception.ExistingCardNumberException;
import br.com.fiap.card_management.ports.exception.OutputPortException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static br.com.fiap.card_management.utils.MessageEnumUtils.*;

@ControllerAdvice
public class CardControllerAdvice {

  @ExceptionHandler(
          {
                  CardLimitException.class
          }
  )
  public ResponseEntity<MessageEntity> handleForbiddenWithCardLimitException(RuntimeException exception) {

    return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(
                    MessageEntity
                            .builder()
                            .title(TIME_DOMAIN_ERROR)
                            .message(exception.getMessage())
                            .build()
            );

  }

  @ExceptionHandler(
          {
                  HttpMessageNotReadableException.class,
                  MissingRequestHeaderException.class
          }
  )
  public ResponseEntity<MessageEntity> handleInternalServerErrorWithSpringException(Exception exception) {

    return ResponseEntity
            .internalServerError()
            .body(
                    MessageEntity
                            .builder()
                            .title(TITLE_DEFAULT_ERROR)
                            .message(NO_FORMAT_REQUEST.getMessage())
                            .build()
            );

  }

  @ExceptionHandler(
          {
                  OutputPortException.class
          }
  )
  public ResponseEntity<MessageEntity> handleInternalServerErrorWithOutputPortException(RuntimeException exception) {

    return ResponseEntity
            .internalServerError()
            .body(
                    MessageEntity
                            .builder()
                            .title(TITLE_PORTS_ERROR)
                            .message(exception.getMessage())
                            .build()
            );

  }

  @ExceptionHandler(
          {
                  ClientNotFoundException.class,
                  ExistingCardNumberException.class,
                  EntityException.class
          }
  )
  public ResponseEntity<MessageEntity> handleInternalServerErrorWithOtherDomainError(RuntimeException exception) {

    return ResponseEntity
            .internalServerError()
            .body(
                    MessageEntity
                            .builder()
                            .title(TIME_DOMAIN_ERROR)
                            .message(exception.getMessage())
                            .build()
            );

  }

}