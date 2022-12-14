package com.esig.br.demo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esig.br.demo.domain.model.Tarefa;

import lombok.Getter;
import lombok.Setter;

@Component
@ViewScoped
public class FiltroBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.logger(FiltroBean.class);

    @Autowired
    TarefaController tarefaController;

    @Getter @Setter
    private transient Tarefa tarefa = new Tarefa();
    private transient List<Tarefa> tarefas;

    public List<Tarefa> getTarefas() {
        tarefas = new ArrayList<>();
        try {
            tarefas.addAll(tarefaController.getTarefas());
            
            if(tarefa.getId() != null) filterByNumero();
            if(tarefa.getDescricao() != null) filterByTituloOuDescricao();
            if(tarefa.getResponsavel() != null) filterByResponsavel();
            if(tarefa.getSituacao() != null) filterBySituacao();

        } catch (Exception e) {
            logger.error("Falha ao tentar filtrar as tarefas. causa: "+e.getClass().getSimpleName());
        }

        return tarefas;
    }

    private void filterBySituacao() {
        tarefas = tarefas.stream().filter(t-> t.getSituacao().equals(tarefa.getSituacao())).collect(Collectors.toList());
    }

    private void filterByResponsavel() {
        tarefas = tarefas.stream().filter(t-> t.getResponsavel().getNome().equals(tarefa.getResponsavel().getNome())).collect(Collectors.toList());
    }

    private void filterByTituloOuDescricao() {
        tarefas = tarefas.stream().filter(t-> { 
            String text = "";
            String descricao = tarefa.getDescricao().toLowerCase();
            
            if(t.getTitulo() != null) text = t.getTitulo().toLowerCase();
            if(t.getDescricao() != null) text += t.getDescricao().toLowerCase();
            
            if(text.length() > descricao.length()){
                return text.contains(descricao);
            }
            
            return descricao.contains(text);
        }).collect(Collectors.toList());
    }

    private void filterByNumero() {
        tarefas = tarefas.stream().filter(t-> t.getId().equals(tarefa.getId())).collect(Collectors.toList());
    }
}
