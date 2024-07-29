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
import me.dio.cardapio.controller.model.CategoriaModel;
import me.dio.cardapio.controller.model.input.CategoriaInput;
import me.dio.cardapio.service.CategoriaService;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping()
	public ResponseEntity<List<CategoriaModel>> obterTodos() {
		var categorias = categoriaService.obterTodos();
		var categoriasModel = categorias.stream().map(CategoriaModel::new).collect(Collectors.toList());
		return ResponseEntity.ok(categoriasModel);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> obterPorId(@PathVariable Integer id) {
		var categoria = categoriaService.obterPorId(id);
		return ResponseEntity.ok(new CategoriaModel(categoria));
	}
	
	@PostMapping()
	public ResponseEntity<CategoriaModel> inserir(@Valid @RequestBody CategoriaInput categoriaInput) {
		var categoria = categoriaService.inserir(categoriaInput.toEntity());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId())
				.toUri();
		
		return ResponseEntity.created(location).body(new CategoriaModel(categoria));
	}
}
