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

import com.esig.br.demo.domain.model.Tarefa;
import com.esig.br.demo.domain.types.Situacao;
import com.esig.br.demo.repository.TarefaRepository;

import lombok.Getter;
import lombok.Setter;


@Component
@ViewScoped
@Join(path = "/nova-tarefa", to = "/tarefa-form.jsf")
public class TarefaController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String UPDATE_JSF_MENSSAGE = "form:messages";
    private static final String UPDATE_JSF_LISTTABLE = "form:listaDeTarefas";
    
    @Autowired
    private transient TarefaRepository tarefaRepository;
    
    @Autowired
    private transient ResponsavelController responsavelController;

    @Getter @Setter
    private transient Tarefa tarefa = new Tarefa();
    @Getter
    private transient List<Tarefa> tarefas;
    
    @PostConstruct
    public void loadData() {
        tarefas = tarefaRepository.findAll();
    }

    public String salvar() {
        boolean updated = tarefa.getId() != null;
        tarefa.setResponsavel(responsavelController.saveOrUpdateAndFlush(tarefa.getResponsavel()));
        tarefa.setSituacao(Situacao.EM_ANDAMENTO);
        tarefaRepository.save(tarefa);
        tarefa = new Tarefa();
        if(updated){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa Atualizada"));
            PrimeFaces.current().executeScript("PF('modalEditar').hide()");
            PrimeFaces.current().ajax().update(UPDATE_JSF_MENSSAGE, UPDATE_JSF_LISTTABLE);
        }
        return "/tarefa-list.xhtml?faces-redirect=true";
    }

    public void deletar() {
        tarefaRepository.delete(tarefa);
        tarefas.remove(tarefa);
        tarefa = new Tarefa();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa Excluida"));
        PrimeFaces.current().ajax().update(UPDATE_JSF_MENSSAGE, UPDATE_JSF_LISTTABLE);
    }

    public void concluir() {
        tarefa.setSituacao(Situacao.FINALIZADO);
        tarefaRepository.save(tarefa);
        tarefa = new Tarefa();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa Concluida"));
        PrimeFaces.current().ajax().update(UPDATE_JSF_MENSSAGE, UPDATE_JSF_LISTTABLE);
    }
}
