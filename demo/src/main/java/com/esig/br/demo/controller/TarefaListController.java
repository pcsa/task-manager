package com.esig.br.demo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esig.br.demo.model.Tarefa;
import com.esig.br.demo.repository.TarefaRepository;

@Component
@ViewScoped
@Join(path = "/", to = "/tarefa-list.jsf")
public class TarefaListController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private TarefaRepository tarefaRepository;

    private List<Tarefa> tarefas;
    
    @PostConstruct
    public void loadData() {
        tarefas = tarefaRepository.findAll();
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }
    
}
