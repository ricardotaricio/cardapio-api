package me.dio.cardapio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.cardapio.entity.Cardapio;

public interface CardapioRepository extends JpaRepository<Cardapio, Integer> {
	
	boolean existsByNome(String nome);
}
