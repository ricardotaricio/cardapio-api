package me.dio.cardapio.controller.model;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import me.dio.cardapio.entity.Cardapio;

public record CardapioModel(
		@Schema(example = "10")
		Integer id,

		@Schema(example = "Almoço")
		String nome,

		List<ProdutoModel> produtos) {

	public CardapioModel(Cardapio entity) {
		this(
			entity.getId(),
			entity.getNome(),
			ofNullable(entity.getProdutos()).orElse(emptyList()).stream().map(ProdutoModel::new).collect(toList()));
	}
}
