package com.esig.br.demo.controller;

import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.esig.br.demo.domain.types.Situacao;

@Named
public class SituacaoController {
    public SelectItem[] getOptions() {
        SelectItem[] items = new SelectItem[Situacao.values().length];
        int i = 0;
        for(Situacao s: Situacao.values()) {
            items[i++] = new SelectItem(s, s.getNome());
        }
        return items;
    }
}
