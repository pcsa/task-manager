package com.esig.br.demo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esig.br.demo.domain.model.Tarefa;
import com.esig.br.demo.domain.types.Situacao;
import com.esig.br.demo.repository.TarefaRepository;

import lombok.Getter;
import lombok.Setter;


@Component
@ViewScoped
public class TarefaController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String UPDATE_JSF_MENSSAGE = "formList:messages";
    private static final String UPDATE_JSF_LISTTABLE = "formList:listaDeTarefas";
    
    @Autowired
    private transient TarefaRepository tarefaRepository;
    
    @Autowired
    private transient ResponsavelController responsavelController;

    @Getter @Setter
    private transient Tarefa tarefa = new Tarefa();
    @Getter @Setter
    private transient List<Tarefa> tarefas = new ArrayList<>();
    @Getter @Setter
    private transient List<Tarefa> tarefasFiltradas = new ArrayList<>();
    
    @PostConstruct
    public void loadData() {
        tarefas = tarefaRepository.findAll();
        tarefasFiltradas.addAll(tarefas);
    }

    public String salvar() {
        boolean updated = tarefa.getId() != null;
        tarefa.setResponsavel(responsavelController.saveOrUpdateAndFlush(tarefa.getResponsavel()));
        tarefa.setSituacao(Situacao.EM_ANDAMENTO);
        tarefaRepository.saveAndFlush(tarefa);
        tarefa = new Tarefa();
        if(updated){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa Atualizada"));
            PrimeFaces.current().executeScript("PF('modalEditar').hide()");
            PrimeFaces.current().ajax().update(UPDATE_JSF_MENSSAGE, UPDATE_JSF_LISTTABLE);
        }
        return "/tarefa-list.jsf?faces-redirect=true";
    }

    public void deletar() {
        if(tarefa.getId()!=null){
            tarefaRepository.delete(tarefa);
            tarefas.remove(tarefa);
            tarefasFiltradas.remove(tarefa);
            tarefa = new Tarefa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa Excluida"));
            PrimeFaces.current().ajax().update(UPDATE_JSF_MENSSAGE, UPDATE_JSF_LISTTABLE);
        }
    }

    public void concluir() {
        if(tarefa.getSituacao()==null || tarefa.getSituacao().equals(Situacao.FINALIZADO)) return;
        tarefa.setSituacao(Situacao.FINALIZADO);
        tarefaRepository.saveAndFlush(tarefa);
        tarefa = new Tarefa();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa Concluida"));
        PrimeFaces.current().ajax().update(UPDATE_JSF_MENSSAGE, UPDATE_JSF_LISTTABLE);
    }

    public Date getDataAtual() {
        return new Date();
    }
}
