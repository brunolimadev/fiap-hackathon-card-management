package br.com.fiap.card_management.utils;

import br.com.fiap.card_management.domain.entities.CardEntity;
import br.com.fiap.card_management.mock.CardEntityMock;
import br.com.fiap.card_management.mock.CardModelMock;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConvertDomainEntityToJpaModelUtilsTest {

  @Test
  void shouldCreateItemEntityGivenAItemModel() {

    //Arrange
    var itemModel = CardModelMock.get();
    var expectItemEntity = CardEntityMock.get();

    //Act
    var itemEntityConverted = ConvertJpaModelToDomainEntityUtils.convert(itemModel);

    //Assert
    assertThat(itemEntityConverted)
            .isInstanceOf(CardEntity.class);

    assertThat(itemEntityConverted.getId()).isEqualTo(expectItemEntity.getId());
    assertThat(itemEntityConverted.getCpf()).isEqualTo(expectItemEntity.getCpf());
    assertThat(itemEntityConverted.getLimit()).isEqualTo(expectItemEntity.getLimit());
    assertThat(itemEntityConverted.getNumber()).isEqualTo(expectItemEntity.getNumber());
    assertThat(itemEntityConverted.getExpiryDate()).isEqualTo(expectItemEntity.getExpiryDate());
    assertThat(itemEntityConverted.getCvv()).isEqualTo(expectItemEntity.getCvv());

  }

}