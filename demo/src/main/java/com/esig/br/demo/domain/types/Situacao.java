package com.esig.br.demo.domain.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Situacao {
    EM_ANDAMENTO("Em Andamento"),
    FINALIZADO("Finalizado");

    @Getter
    private String nome;
}
