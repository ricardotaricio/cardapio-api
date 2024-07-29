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
import me.dio.cardapio.controller.model.ProdutoModel;
import me.dio.cardapio.controller.model.input.ProdutoInput;
import me.dio.cardapio.service.ProdutoService;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;

	@GetMapping()
	public ResponseEntity<List<ProdutoModel>> getAll() {
		var produtos = produtoService.obterTodos();
		var produtosModel = produtos.stream().map(ProdutoModel::new).collect(Collectors.toList());
		return ResponseEntity.ok(produtosModel);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> getById(@PathVariable Integer id) {
		var produto = produtoService.obterPorId(id);
		return ResponseEntity.ok(new ProdutoModel(produto));
	}
	
	@PostMapping()
	public ResponseEntity<ProdutoModel> add(@Valid @RequestBody ProdutoInput produtoInput) {
		var produto = produtoService.inserir(produtoInput.toEntity());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(produto.getId())
				.toUri();
		
		return ResponseEntity.created(location).body(new ProdutoModel(produto));
	}
}
