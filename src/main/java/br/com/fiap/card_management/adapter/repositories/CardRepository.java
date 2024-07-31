package br.com.fiap.card_management.adapter.repositories;

import br.com.fiap.card_management.adapter.repositories.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardModel, Long> {}