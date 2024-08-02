package br.com.fiap.card_management.controller;

import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.ports.exception.OutputPortException;
import br.com.fiap.card_management.ports.outputport.CardManagementOutputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.fiap.card_management.utils.ValidateEntityValuesUtils.validateValues;


@RestController
@RequestMapping("cartao")
@Tag(name = "Card Controller", description = "Customer can manage card through API resources")
public class CardController {

  private final CardManagementOutputPort cardManagementOutputPort;

  public CardController(
          CardManagementOutputPort cardManagementOutputPort
  ) {
    
    this.cardManagementOutputPort = cardManagementOutputPort;

  }

  @Operation(summary = "Create card")
  @ApiResponse(
          responseCode = "200",
          description = "No content return"
  )
  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void createCard(@RequestBody CardEntity cardEntity) throws OutputPortException {

    validateValues(cardEntity);
    cardManagementOutputPort.createCard(cardEntity);

  }

  @Operation(summary = "Returns a card by number")
  @ApiResponse(responseCode = "200", description = "Gets a specific card")
  @GetMapping(value = "{numero}")
  public ResponseEntity<CardEntity> getCard(
          @PathVariable("numero") String number) throws OutputPortException {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(cardManagementOutputPort.getCard(number));

  }

}