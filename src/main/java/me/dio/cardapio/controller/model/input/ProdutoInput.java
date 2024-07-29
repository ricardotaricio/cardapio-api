package me.dio.cardapio.controller.model.input;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import me.dio.cardapio.entity.Categoria;
import me.dio.cardapio.entity.Produto;

public record ProdutoInput(
		@NotBlank
		@Schema(example = "Suco de laranja")
		String descricao,

		@NotNull
		@Positive
		@Schema(example = "8.50")
		BigDecimal preco,

		@Valid
		@NotNull
		CategoriaIdInput categoria) {
	
	public Produto toEntity() {
		Categoria categoriaEntity = new Categoria();
		categoriaEntity.setId(categoria.id());
		
		Produto produto = new Produto();
		produto.setDescricao(this.descricao);
		produto.setPreco(preco);
		produto.setCategoria(categoriaEntity);
		return produto;
	}
}
