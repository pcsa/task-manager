package com.esig.br.demo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esig.br.demo.domain.model.Responsavel;
import com.esig.br.demo.domain.model.Tarefa;
import com.esig.br.demo.domain.types.Situacao;
import com.esig.br.demo.repository.ResponsavelRepository;
import com.esig.br.demo.repository.TarefaRepository;

import lombok.Getter;
import lombok.Setter;

@Component
@ViewScoped
@Join(path = "/nova-tarefa", to = "/tarefa-form.jsf")
@Getter
@Setter
public class TarefaController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;
    
    private Tarefa tarefa = new Tarefa();

    private List<Tarefa> tarefas;
    private List<Responsavel> responsaveis;
    
    @PostConstruct
    public void loadData() {
        tarefas = tarefaRepository.findAll();
        responsaveis = responsavelRepository.findAll();
    }

    public String salvar() {
        boolean updated = tarefa.getId() != null;
        tarefaRepository.save(tarefa);
        tarefa = new Tarefa();
        if(updated){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa Atualizada"));
            PrimeFaces.current().executeScript("PF('modalEditar').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:listaDeTarefas");
        }
        return "/tarefa-list.xhtml?faces-redirect=true";
    }

    public void deletar() {
        tarefaRepository.delete(tarefa);
        tarefas.remove(tarefa);
        tarefa = new Tarefa();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa Excluida"));
        PrimeFaces.current().ajax().update("form:messages", "form:listaDeTarefas");
    }

    public void concluir() {
        tarefa.setSituacao(Situacao.FINALIZADO);
        tarefaRepository.save(tarefa);
        tarefa = new Tarefa();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa Concluida"));
        PrimeFaces.current().ajax().update("form:messages", "form:listaDeTarefas");
    }
}
