package com.udemy.expert.exercicios.controller;

import com.udemy.expert.exercicios.domain.entity.ItemPedido;
import com.udemy.expert.exercicios.domain.entity.Pedido;
import com.udemy.expert.exercicios.dto.DadosItemPedidoDTO;
import com.udemy.expert.exercicios.dto.InformacoesPedidosDTO;
import com.udemy.expert.exercicios.dto.PedidoDTO;
import com.udemy.expert.exercicios.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedidoSalvo = pedidoService.salvar(dto);
        return pedidoSalvo.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidosDTO getById( @PathVariable Integer id ){
        return pedidoService
                .obterPedidoCompleto(id)
                .map( p -> converterPedidoEmInfodto(p) )
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }


    private InformacoesPedidosDTO converterPedidoEmInfodto(Pedido pedido) {
        return InformacoesPedidosDTO.builder()
                .codigo(pedido.getId())
                .date(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .itens(converterItemEmDadosdto(pedido.getItens()))
                .build();
    }

    private List<DadosItemPedidoDTO> converterItemEmDadosdto(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens
                .stream()
                .map(item -> DadosItemPedidoDTO.builder()
                        .descricao(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()).collect(Collectors.toList());
    }
}
