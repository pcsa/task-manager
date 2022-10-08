package com.esig.br.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.esig.br.demo.domain.model.Responsavel;
import com.esig.br.demo.domain.model.Tarefa;
import com.esig.br.demo.domain.types.Prioridade;
import com.esig.br.demo.domain.types.Situacao;
import com.esig.br.demo.repository.ResponsavelRepository;
import com.esig.br.demo.repository.TarefaRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init (TarefaRepository tarefaRepository, ResponsavelRepository responsavelRepository){
		return args -> {
			List<Responsavel> responsaveis = Arrays.asList(
				new Responsavel(null, "Paulo"),
				new Responsavel(null, "César")
			);
			responsaveis.forEach(responsavelRepository::save);
			List<Tarefa> tarefas = Arrays.asList(
				new Tarefa(null, "Tarefa01", "Uma descrição da tarefa01", responsaveis.get(0), Prioridade.ALTA, Situacao.EM_ANDAMENTO, new Date()),
				new Tarefa(null, "Tarefa02", "Uma descrição da tarefa02", responsaveis.get(0), Prioridade.MEDIA, Situacao.EM_ANDAMENTO, new Date()),
				new Tarefa(null, "Tarefa03", "Uma descrição da tarefa03", responsaveis.get(0), Prioridade.BAIXA, Situacao.EM_ANDAMENTO, new Date())
				);
			tarefas.forEach(tarefaRepository::save);
		};
	}

}
