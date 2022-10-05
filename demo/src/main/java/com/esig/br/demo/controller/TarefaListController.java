package com.esig.br.demo.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
@Join(path = "/", to = "/tarefa-list.jsf")
public class TarefaListController implements Serializable {
    private static final long serialVersionUID = 1L;
}
