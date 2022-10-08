package com.esig.br.demo.controller;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Component;

import com.esig.br.demo.domain.types.Prioridade;

@Component
public class PrioridadeController {
    public SelectItem[] getOptions() {
        SelectItem[] items = new SelectItem[Prioridade.values().length];
        int i = 0;
        for(Prioridade p: Prioridade.values()) {
            items[i++] = new SelectItem(p, p.getNome());
        }
        return items;
    }
}
