package com.esig.br.demo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esig.br.demo.domain.model.Tarefa;
import com.esig.br.demo.domain.types.Situacao;
import com.esig.br.demo.exceptions.TarefaCreateOrUpdateException;
import com.esig.br.demo.exceptions.TarefaDeleteException;
import com.esig.br.demo.repository.TarefaRepository;

import lombok.Getter;
import lombok.Setter;


@Component
@ViewScoped
public class TarefaController implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.logger(TarefaController.class);
    
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
        try {
            tarefas = tarefaRepository.findAll();
            tarefasFiltradas.addAll(tarefas);
        } catch (Exception e) {
            logger.error("Falha ao tentar ler as terefas do banco de dados. causa: "+e.getClass().getSimpleName());
        }
    }

    public String salvar() {
        try {
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
            logger.debug("Tarefa salva com sucesso");
            return "/tarefa-list.jsf?faces-redirect=true";
        } catch (Exception e) {
            throw new TarefaCreateOrUpdateException(e);
        }
    }

    public void deletar() {
        try {
            if(tarefa.getId()!=null){
                tarefaRepository.delete(tarefa);
                tarefas.remove(tarefa);
                tarefasFiltradas.remove(tarefa);
                tarefa = new Tarefa();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa Excluida"));
                PrimeFaces.current().ajax().update(UPDATE_JSF_MENSSAGE, UPDATE_JSF_LISTTABLE);
                logger.debug("Tarefa excluída com sucesso");
            }
        } catch (Exception e) {
            throw new TarefaDeleteException(e);
        }
    }

    public void concluir() {
        try {
            if(tarefa.getSituacao()==null || tarefa.getSituacao().equals(Situacao.FINALIZADO)) return;
            tarefa.setSituacao(Situacao.FINALIZADO);
            tarefaRepository.saveAndFlush(tarefa);
            tarefa = new Tarefa();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa Concluida"));
            PrimeFaces.current().ajax().update(UPDATE_JSF_MENSSAGE, UPDATE_JSF_LISTTABLE);
            logger.debug("Tarefa concluída com sucesso");
        } catch (Exception e) {
            logger.error("Tarefa não pôde ser concluída. causa: "+e.getClass().getSimpleName());
        }
    }

    public Date getDataAtual() {
        return new Date();
    }
}
