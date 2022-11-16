package com.udemy.expert.exercicios.service;

import com.udemy.expert.exercicios.domain.entity.Pedido;
import com.udemy.expert.exercicios.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional obterPedidoCompleto(Integer id);
}
