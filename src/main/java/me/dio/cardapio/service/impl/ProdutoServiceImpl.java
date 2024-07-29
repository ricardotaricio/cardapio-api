package me.dio.cardapio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dio.cardapio.entity.Categoria;
import me.dio.cardapio.entity.Produto;
import me.dio.cardapio.repository.CategoriaRepository;
import me.dio.cardapio.repository.ProdutoRepository;
import me.dio.cardapio.service.ProdutoService;
import me.dio.cardapio.service.exception.ResourceNotFoundException;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Produto> obterTodos() {
		return produtoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Produto obterPorId(Integer id) {
		return produtoRepository.findById(id)
				.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	@Transactional
	public Produto inserir(Produto produto) {
		Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada."));

		produto.setCategoria(categoria);

		return produtoRepository.save(produto);
	}

	@Override
	@Transactional
	public Produto atualizar(Integer id, Produto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		produtoRepository.deleteById(id);
	}

}
