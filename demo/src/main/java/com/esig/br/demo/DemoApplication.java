package com.esig.br.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.esig.br.demo.model.Tarefa;
import com.esig.br.demo.repository.TarefaRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init (TarefaRepository tarefaRepository){
		return args -> {
			List<Tarefa> tarefas = Arrays.asList(
				new Tarefa(null, "Tarefa01", "Uma descrição da tarefa01", "Paulo", "1", new Date()),
				new Tarefa(null, "Tarefa02", "Uma descrição da tarefa02", "Paulo", "1", new Date()),
				new Tarefa(null, "Tarefa03", "Uma descrição da tarefa03", "Paulo", "1", new Date())
				);

			tarefas.forEach(tarefaRepository::save);
		};
	}

}
