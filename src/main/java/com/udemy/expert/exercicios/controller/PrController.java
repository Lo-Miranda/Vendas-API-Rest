package com.udemy.expert.exercicios.controller;


import com.udemy.expert.exercicios.domain.entity.Cliente;
import com.udemy.expert.exercicios.domain.entity.Produto;
import com.udemy.expert.exercicios.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/produto")
public class PrController {
    @Autowired
    ProdutoRepository produtoRepository;


    @PostMapping("cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Produto produtoUpdated){
        produtoRepository
                .findById(id)
                .map(p -> {
                    produtoUpdated.setId(p.getId());
                    produtoRepository.save(produtoUpdated);
                    return produtoUpdated;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        produtoRepository
                .findById(id)
                .map(p -> {
                    produtoRepository.delete(p);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping("{id}")
    public Produto getById(@PathVariable Integer id){
        return produtoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return produtoRepository.findAll(example);

    }

}
