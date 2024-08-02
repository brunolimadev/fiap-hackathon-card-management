package br.com.fiap.card_management.mock;

import java.util.Map;

public class ClientMock {

  public static Map<String, String> get() {

    return Map.of(
            "cpf","11111111111",
            "nome","João da Silva",
            "email","joao@example.com",
            "telefone","+55 11 91234-5678",
            "rua","Rua A",
            "cidade","Cidade",
            "estado","Estado",
            "cep","12345-678",
            "pais","Brasil"
    );

  }

}