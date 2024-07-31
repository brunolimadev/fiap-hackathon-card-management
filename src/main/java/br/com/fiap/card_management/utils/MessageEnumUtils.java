package br.com.fiap.card_management.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum MessageEnumUtils {

  CARD_MANAGEMENT_CREATE_CARD_OUTPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar criar o cartao"),
  CARD_MANAGEMENT_GET_CARD_OUTPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar recuperar o cartao"),
  ENTITY_EXCEPTION("Por favor preencha todos os campos"),
  MESSAGE_EMPTY_BODY_SPRING_EXCEPTION("Por favor insira valores validos");

  public static final String TIME_DOMAIN_EXCEPTION = "Erro de negocio";
  public static final String TITLE_PORTS_EXCEPTION =  "Erro de comunicacao";
  public static final String TITLE_DEFAULT_EXCEPTION =  "Erro";

  private String message;

}