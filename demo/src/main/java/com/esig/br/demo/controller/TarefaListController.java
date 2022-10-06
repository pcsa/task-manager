package com.esig.br.demo.controller;

import java.io.Serializable;

import org.ocpsoft.rewrite.annotation.Join;

@Join(path = "/", to = "/tarefa-list.jsf")
public class TarefaListController implements Serializable {
    private static final long serialVersionUID = 1L;
}
