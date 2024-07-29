package me.dio.cardapio.controller.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import me.dio.cardapio.entity.Cardapio;

public record CardapioInput(
		@NotBlank
		@Schema(example = "Almoço")
		String nome) {
	
	public Cardapio toEntity() {
		Cardapio cardapio = new Cardapio();
		cardapio.setNome(this.nome);
		return cardapio;
	}

}
