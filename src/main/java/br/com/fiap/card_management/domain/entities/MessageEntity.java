package br.com.fiap.card_management.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MessageEntity {

  private String title;
  private String message;

}