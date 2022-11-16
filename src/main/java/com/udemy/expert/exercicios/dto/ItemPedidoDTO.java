package com.udemy.expert.exercicios.dto;

import lombok.Builder;

@Builder
public class ItemPedidoDTO {
    private Integer produto;
    private Integer quantidade;

    public ItemPedidoDTO() {
    }

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
