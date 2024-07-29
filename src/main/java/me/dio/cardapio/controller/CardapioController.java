package me.dio.cardapio.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import me.dio.cardapio.controller.model.CardapioModel;
import me.dio.cardapio.controller.model.input.CardapioInput;
import me.dio.cardapio.service.CardapioService;

@RestController
@RequestMapping("/cardapios")
public class CardapioController {

	@Autowired
	private CardapioService cardapioService;
	
	@GetMapping()
	public ResponseEntity<List<CardapioModel>> listarTodos() {
		var cardapios = cardapioService.obterTodos();
		var cardapiosModel = cardapios.stream().map(CardapioModel::new).collect(Collectors.toList());
		return ResponseEntity.ok(cardapiosModel);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CardapioModel> obterPorId(@PathVariable int id) {
		var cardapio = cardapioService.obterPorId(id);
		return ResponseEntity.ok(new CardapioModel(cardapio));
	}
	
	@PostMapping()
	public ResponseEntity<CardapioModel> inserir(@Valid @RequestBody CardapioInput cardapioInput) {
		var cardapio = cardapioService.inserir(cardapioInput.toEntity());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cardapio.getId())
				.toUri();

		return ResponseEntity.created(location).body(new CardapioModel(cardapio));
	}
	
	@PostMapping("/{cardapioId}/produtos/{produtoId}")
	public ResponseEntity<CardapioModel> associarProduto(@PathVariable Integer cardapioId, @PathVariable Integer produtoId) {
		var cardapio = cardapioService.associarProduto(cardapioId, produtoId);
		return ResponseEntity.ok(new CardapioModel(cardapio));
	}
}
