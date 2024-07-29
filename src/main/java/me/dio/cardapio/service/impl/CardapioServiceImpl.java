package me.dio.cardapio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dio.cardapio.entity.Cardapio;
import me.dio.cardapio.entity.Produto;
import me.dio.cardapio.repository.CardapioRepository;
import me.dio.cardapio.repository.ProdutoRepository;
import me.dio.cardapio.service.CardapioService;
import me.dio.cardapio.service.exception.BusinessException;
import me.dio.cardapio.service.exception.ResourceNotFoundException;

@Service
public class CardapioServiceImpl implements CardapioService {
	@Autowired
	private CardapioRepository cardapioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Cardapio> obterTodos() {
		return cardapioRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cardapio obterPorId(Integer id) {
		return cardapioRepository.findById(id)
				.orElseThrow(ResourceNotFoundException::new);
	}
	
	@Override
	@Transactional
	public Cardapio inserir(Cardapio cardapio) {
		if (cardapioRepository.existsByNome(cardapio.getNome()))
			throw new BusinessException("Cardapio já existe.");
	
		return cardapioRepository.save(cardapio);
	}
	
	@Override
	@Transactional
	public Cardapio atualizar(Integer id, Cardapio entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		cardapioRepository.deleteById(id);	
	}

	@Override
	@Transactional
	public Cardapio associarProduto(Integer cardapioId, Integer produtoId) {
		Cardapio cardapio = cardapioRepository.findById(cardapioId)
				.orElseThrow(() -> new ResourceNotFoundException("Cardapio não encontrado."));

		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));
		
		cardapio.getProdutos().add(produto);

		return cardapioRepository.save(cardapio);
	}
}
