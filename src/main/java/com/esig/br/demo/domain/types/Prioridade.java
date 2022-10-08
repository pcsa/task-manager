package com.esig.br.demo.domain.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Prioridade {
    ALTA("Alta"),
    MEDIA("Média"),
    BAIXA("Baixa");
    
    @Getter
    private String nome;
}
