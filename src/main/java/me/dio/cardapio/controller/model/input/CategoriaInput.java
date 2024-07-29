package me.dio.cardapio.controller.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import me.dio.cardapio.entity.Categoria;

public record CategoriaInput(
		@NotEmpty
		@Schema(example = "Bebidas")
		String nome) {

	public Categoria toEntity() {
		Categoria categoria = new Categoria();
		categoria.setNome(nome);
		return categoria;
	}
}
