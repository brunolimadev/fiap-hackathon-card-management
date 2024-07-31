package br.com.fiap.card_management.adapter.ports.outputport;

import br.com.fiap.card_management.ports.outputport.ClientManagementOutputPort;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ClientManagementOutputPortAdapter implements ClientManagementOutputPort {

  private final WebClient client = WebClient.create("http://localhost:8085");

  @Override
  public Object getClient(String sessionId) {

    Mono<Object> response = client.get()
            .uri("/ecommerce-management/api/v1/sessions/{id}", sessionId)
            .retrieve()
            .bodyToMono(Object.class);

    return response.block();

  }

}