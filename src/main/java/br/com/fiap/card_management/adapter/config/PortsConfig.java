package br.com.fiap.card_management.adapter.config;

import br.com.fiap.card_management.adapter.ports.outputport.CardManagementOutputPortAdapter;
import br.com.fiap.card_management.adapter.ports.outputport.ClientManagementOutputPortAdapter;
import br.com.fiap.card_management.adapter.repositories.CardRepository;
import br.com.fiap.card_management.ports.outputport.CardManagementOutputPort;
import br.com.fiap.card_management.ports.outputport.ClientManagementOutputPort;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortsConfig {

  @Value("${client-management.base-url}")
  private String clientBaseUrl;

  @Bean
  public ClientManagementOutputPort sessionManagementOutputPort() {

    return new ClientManagementOutputPortAdapter(clientBaseUrl);

  }

  @Bean
  public CardManagementOutputPort itemManagementOutputPort(
      CardRepository cardRepository,
      ClientManagementOutputPort clientManagementOutputPort) {

    return new CardManagementOutputPortAdapter(cardRepository, clientManagementOutputPort);

  }

}
