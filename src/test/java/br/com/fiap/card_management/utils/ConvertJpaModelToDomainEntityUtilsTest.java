package br.com.fiap.card_management.utils;

import br.com.fiap.card_management.adapter.repositories.model.CardModel;
import br.com.fiap.card_management.mock.CardEntityMock;
import br.com.fiap.card_management.mock.CardModelMock;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConvertJpaModelToDomainEntityUtilsTest {

  @Test
  void shouldCreateItemModelGivenAItemEntity() {

    //Arrange
    var itemEntity = CardEntityMock.get();
    var expectItemModel = CardModelMock.get();

    //Act
    var itemModelConverted = ConvertDomainEntityToJpaModelUtils.convert(itemEntity);

    //Assert
    assertThat(itemModelConverted)
            .isInstanceOf(CardModel.class);

    assertThat(itemModelConverted.getId()).isEqualTo(expectItemModel.getId());
    assertThat(itemModelConverted.getCpf()).isEqualTo(expectItemModel.getCpf());
    assertThat(itemModelConverted.getNumber()).isEqualTo(expectItemModel.getNumber());

  }

}