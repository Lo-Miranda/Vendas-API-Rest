package com.udemy.expert.exercicios.domain.repository;

import com.udemy.expert.exercicios.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemPedido, Integer> {
}
