package me.dio.cardapio.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import me.dio.cardapio.entity.Categoria;

public record CategoriaModel(
		@Schema(example = "1")
		Integer id,
		
		@Schema(example = "Bebidas")
		String nome) {

	public CategoriaModel(Categoria entity) {
		this(
			entity.getId(),
			entity.getNome());
	}
}
