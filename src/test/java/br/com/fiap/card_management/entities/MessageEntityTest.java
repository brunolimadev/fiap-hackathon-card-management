package br.com.fiap.card_management.entities;

import br.com.fiap.card_management.domain.entities.MessageEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MessageEntityTest {

  @Test
  void shouldCreateMessageEntity() {

    assertThat(MessageEntity
            .builder()
            .title("Título")
            .message("Mensagem")
            .build()
    )
            .isNotNull();

  }

}