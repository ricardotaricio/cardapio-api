package me.dio.cardapio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dio.cardapio.entity.Categoria;
import me.dio.cardapio.repository.CategoriaRepository;
import me.dio.cardapio.service.CategoriaService;
import me.dio.cardapio.service.exception.BusinessException;
import me.dio.cardapio.service.exception.ResourceNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> obterTodos() {
		return categoriaRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Categoria obterPorId(Integer id) {
		return categoriaRepository.findById(id)
				.orElseThrow(ResourceNotFoundException::new);
	}
	
	@Override
	@Transactional
	public Categoria inserir(Categoria categoria) {
		if (categoriaRepository.existsByNome(categoria.getNome()))
			throw new BusinessException("Categoria já existe.");

		return categoriaRepository.save(categoria);
	}

	@Override
	@Transactional
	public Categoria atualizar(Integer id, Categoria entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		categoriaRepository.deleteById(id);
	}
}
