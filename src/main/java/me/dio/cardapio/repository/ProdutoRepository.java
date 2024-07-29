package me.dio.cardapio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.cardapio.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
