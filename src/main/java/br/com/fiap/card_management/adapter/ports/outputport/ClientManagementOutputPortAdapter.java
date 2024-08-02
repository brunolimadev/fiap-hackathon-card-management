package br.com.fiap.card_management.adapter.ports.outputport;

import br.com.fiap.card_management.ports.outputport.ClientManagementOutputPort;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

public class ClientManagementOutputPortAdapter implements ClientManagementOutputPort {

//  private final WebClient client = WebClient.create("http://localhost:8082");

  @Override
  public Object getClient(String cpf) {

//    Mono<Object> response = client.get()
//            .uri("/api/cliente/{cpf}", cpf)
//            .retrieve()
//            .bodyToMono(Object.class);
//
//    return response.block();

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