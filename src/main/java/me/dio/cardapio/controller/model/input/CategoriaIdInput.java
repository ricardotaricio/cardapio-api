package me.dio.cardapio.controller.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CategoriaIdInput(
		@NotNull
		@Positive
		@Schema(example = "1")
		Integer id) {

}
