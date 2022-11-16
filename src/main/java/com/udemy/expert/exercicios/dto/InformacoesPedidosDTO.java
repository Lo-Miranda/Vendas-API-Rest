package com.udemy.expert.exercicios.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;


@Builder
public class InformacoesPedidosDTO {

    private Integer codigo;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;

    private String date;
    private List<DadosItemPedidoDTO> itens;


    public InformacoesPedidosDTO() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DadosItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<DadosItemPedidoDTO> itens) {
        this.itens = itens;
    }
}
