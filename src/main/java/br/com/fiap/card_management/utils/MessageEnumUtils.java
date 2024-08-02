package br.com.fiap.card_management.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum MessageEnumUtils {

  ERROR_TO_CREATE_CARD("Ocorreu um erro ao tentar criar o cartao"),
  ERROR_TO_GET_CARD("Ocorreu um erro ao tentar recuperar o cartao"),
  MANDATORY_VALUES("Por favor preencha todos os campos"),
  NO_FORMAT_REQUEST("Por favor insira valores validos"),
  CLIENT_NOT_FOUND("Cliente nao encontrado"),
  CARD_LIMIT_REACHED("O cliente atingiu a quantidade maxima de cartoes permitida"),
  EXISTING_CARD_NUMBER("Ja existe um cartao registrado com o numero informado"),
  NEGATIVE_CARD_LIMIT("O limite do cartao nao pode ser negativo"),
  INVALID_CPF_NUMBER("Por favor insira um numero de CPF valido"),
  INVALID_CPF_LENGTH("Tamanho de CPF invalido (O CPF deve conter no maximo 11 caracteres)");

  public static final String TIME_DOMAIN_ERROR = "Erro de negocio";
  public static final String TITLE_PORTS_ERROR =  "Erro de comunicacao";
  public static final String TITLE_DEFAULT_ERROR =  "Erro";

  private String message;

}