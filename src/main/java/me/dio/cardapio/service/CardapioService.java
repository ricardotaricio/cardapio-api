package me.dio.cardapio.service;

import me.dio.cardapio.entity.Cardapio;

public interface CardapioService extends CrudService<Cardapio, Integer> {

	Cardapio associarProduto(Integer cardapioId, Integer produtoId);

}
