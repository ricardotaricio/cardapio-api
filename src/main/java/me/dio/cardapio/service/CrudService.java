package me.dio.cardapio.service;

import java.util.List;

public interface CrudService<T, ID> {
	List<T> obterTodos();
	T inserir(T cardapio);
	T obterPorId(ID id);
	T atualizar(ID id, T entity);
	void delete(ID id);
}
