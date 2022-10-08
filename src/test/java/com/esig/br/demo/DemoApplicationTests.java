package com.esig.br.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.esig.br.demo.domain.model.Responsavel;
import com.esig.br.demo.domain.model.Tarefa;
import com.esig.br.demo.domain.types.Prioridade;
import com.esig.br.demo.domain.types.Situacao;
import com.esig.br.demo.repository.ResponsavelRepository;
import com.esig.br.demo.repository.TarefaRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class DemoApplicationTests {
	
	@Autowired
	TarefaRepository tarefaRepository;

	@Autowired
	ResponsavelRepository responsavelRepository;

	private Tarefa tarefa;
	private Responsavel responsavel;

	@BeforeAll
	public void setupTest() {
		tarefa = new Tarefa(null,"TestTarefa", "Testando função salvar no banco", null, Prioridade.ALTA, Situacao.EM_ANDAMENTO, new Date());
		responsavel = new Responsavel(null, "TestResponsavel");
	}

	@Test
	public void testResponsavelSalvoTemID() {
		Responsavel responsavelSalvo = responsavelRepository.saveAndFlush(responsavel);

		assertNotNull(responsavelSalvo.getId());
	}

	@Test
	public void testResponsavelSalvoTemMesmoNome() {
		Responsavel responsavelSalvo = responsavelRepository.saveAndFlush(responsavel);

		assertEquals(responsavelSalvo.getNome(), responsavelSalvo.getNome());
	}

	@Test
	public void testResponsavelSalvoEIgual() {
		Responsavel responsavelSalvo = responsavelRepository.saveAndFlush(responsavel);

		assertEquals(responsavelSalvo.getNome(), responsavelSalvo.getNome());
	}

	@Test
	public void testTarefaSalvaTemID() {
		Tarefa tarefaSalva = tarefaRepository.saveAndFlush(tarefa);
		
		assertNotNull(tarefaSalva.getId());
	}

	@Test
	public void testTarefaSalvaTemMesmoTitulo() {
		Tarefa tarefaSalva = tarefaRepository.saveAndFlush(tarefa);
		
		assertEquals(tarefaSalva.getTitulo(), tarefa.getTitulo());
	}

	@Test
	public void testTarefaSalvaTemMesmaDescricao() {
		Tarefa tarefaSalva = tarefaRepository.saveAndFlush(tarefa);
		
		assertEquals(tarefaSalva.getDescricao(), tarefa.getDescricao());
	}

	@Test
	public void testTarefaSalvaTemMesmoResponsavel() {
		Tarefa tarefaSalva = tarefaRepository.saveAndFlush(tarefa);
		
		assertEquals(tarefaSalva.getResponsavel(), tarefa.getResponsavel());
	}

	@Test
	public void testTarefaSalvaTemMesmaPrioridade() {
		Tarefa tarefaSalva = tarefaRepository.saveAndFlush(tarefa);
		
		assertEquals(tarefaSalva.getPrioridade(), tarefa.getPrioridade());
	}

	@Test
	public void testTarefaSalvaTemMesmaSituacao() {
		Tarefa tarefaSalva = tarefaRepository.saveAndFlush(tarefa);
		
		assertEquals(tarefaSalva.getSituacao(), tarefa.getSituacao());
	}

	@Test
	public void testTarefaSalvaTemMesmaDeadline() {
		Tarefa tarefaSalva = tarefaRepository.saveAndFlush(tarefa);
		
		assertEquals(tarefaSalva.getDeadline(), tarefa.getDeadline());
	}

	@Test
	public void testTarefaSalvaEIgual() {
		Tarefa tarefaSalva = tarefaRepository.saveAndFlush(tarefa);
		
		assertEquals(tarefaSalva, tarefa);
	}
}
