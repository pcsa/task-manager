package com.esig.br.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esig.br.demo.domain.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    
}
