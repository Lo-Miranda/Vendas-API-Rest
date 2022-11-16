package com.udemy.expert.exercicios.service.impl;

import com.udemy.expert.exercicios.domain.entity.Cliente;
import com.udemy.expert.exercicios.domain.entity.ItemPedido;
import com.udemy.expert.exercicios.domain.entity.Pedido;
import com.udemy.expert.exercicios.domain.entity.Produto;
import com.udemy.expert.exercicios.domain.repository.ClienteRepository;
import com.udemy.expert.exercicios.domain.repository.ItemRepository;
import com.udemy.expert.exercicios.domain.repository.PedidoRepository;
import com.udemy.expert.exercicios.domain.repository.ProdutoRepository;
import com.udemy.expert.exercicios.dto.ItemPedidoDTO;
import com.udemy.expert.exercicios.dto.PedidoDTO;
import com.udemy.expert.exercicios.exception.RegraNegocioException;
import com.udemy.expert.exercicios.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PedidoServImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemRepository itemRepository;

    //salvar pedidos:
    @Override
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(()
                -> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

       List<ItemPedido> itensPedidos = converterItens(pedido, dto.getItens());
       pedidoRepository.save(pedido);
       itemRepository.saveAll(itensPedidos);
       pedido.setItens(itensPedidos);

       return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findById(id);
    }

    //converter itens em ItensPedidos:
    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if(itens.isEmpty()){
            throw new RuntimeException("Não é possível realizar o pedido sem itens");
        }
        return itens
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository
                                                    .findById(idProduto)
                                                    .orElseThrow(() -> new RuntimeException
                                                            ("Código de produto inválido: " + idProduto));


                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    return itemPedido;

                }).collect(Collectors.toList());

    }
}
