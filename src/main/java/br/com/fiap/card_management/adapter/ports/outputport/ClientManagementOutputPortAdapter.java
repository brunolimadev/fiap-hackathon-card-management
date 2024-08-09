package br.com.fiap.card_management.adapter.ports.outputport;

import br.com.fiap.card_management.ports.outputport.ClientManagementOutputPort;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ClientManagementOutputPortAdapter implements ClientManagementOutputPort {

  private final WebClient client;

  public ClientManagementOutputPortAdapter(@Value("${client-management.base-url}") String baseUrl) {

    this.client = WebClient.create(baseUrl);

  }

  @Override
  public Object getClient(String cpf) {

    Mono<Object> response = client.get()
        .uri("/api/cliente/{cpf}", cpf)
        .retrieve()
        .bodyToMono(Object.class);

    return response.block();

  }

}