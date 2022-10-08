package com.esig.br.demo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esig.br.demo.domain.model.Responsavel;
import com.esig.br.demo.repository.ResponsavelRepository;

@Component
@ViewScoped
public class ResponsavelController implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String UPDATE_JSF_LISTFILTER = "filtragemDeTarefas:responsavelFiltro";

    @Autowired
    transient ResponsavelRepository responsavelRepository;

    private transient Map<String,Responsavel> responsaveisMap = new HashMap<>();

    @PostConstruct
    public void loadData() {
        List<Responsavel> responsaveis = responsavelRepository.findAll();
        responsaveis.forEach(r -> responsaveisMap.put(r.getNome(), r));
    }

    public Responsavel saveOrUpdateAndFlush(Responsavel responsavel){
        if(responsavel == null) return null;
        if(!responsaveisMap.containsKey(responsavel.getNome())) {
            responsavel = responsavelRepository.saveAndFlush(responsavel);
            responsaveisMap.put(responsavel.getNome(), responsavel);
        }
        PrimeFaces.current().ajax().update(UPDATE_JSF_LISTFILTER);
        return responsaveisMap.get(responsavel.getNome());
    }

    public List<Responsavel> getResponsaveis(){
        return new ArrayList<>(responsaveisMap.values());
    }
}
