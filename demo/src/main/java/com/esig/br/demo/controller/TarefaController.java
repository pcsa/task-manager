package com.esig.br.demo.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esig.br.demo.model.Tarefa;
import com.esig.br.demo.repository.TarefaRepository;

@Component
@ViewScoped
@Join(path = "/tarefa", to = "/tarefa-form.jsf")
public class TarefaController implements Serializable{
    private static final long serialVersionUID = 1L;

    @Autowired
    private TarefaRepository tarefaRepository;

    private Tarefa tarefa = new Tarefa();

    public String save() {
        tarefaRepository.save(tarefa);
        tarefa = new Tarefa();
        return "/tarefa-list.xhtml?faces-redirect=true";
    }

    public Tarefa getTarefa() {
        return tarefa;
    }
}
