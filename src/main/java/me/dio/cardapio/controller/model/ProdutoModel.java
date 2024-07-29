package me.dio.cardapio.controller.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import me.dio.cardapio.entity.Produto;

public record ProdutoModel(
		@Schema(example = "1")
		Integer id,

		@Schema(example = "Suco de laranja")
		String descricao,

		@Schema(example = "8.50")
		BigDecimal preco,

		CategoriaModel categoria) {
	
	public ProdutoModel(Produto entity) {
		this(
			entity.getId(),
			entity.getDescricao(),
			entity.getPreco(),
			new CategoriaModel(entity.getCategoria()));
	}

}
