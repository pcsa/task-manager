package com.esig.br.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.esig.br.demo.domain.model.Responsavel;

@FacesConverter(forClass=Responsavel.class)
public class ResponsavelConverter implements Converter<Object>{

    private static Map<String, Responsavel> responsaveis = new HashMap<>(); 

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return responsaveis.getOrDefault(value, new Responsavel(null, value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Responsavel responsavel = (Responsavel) value;
        if(responsaveis.containsValue(responsavel)){
            return responsavel.getNome();
        }
        responsaveis.put(responsavel.getNome(), responsavel);
        return responsavel.getNome();
    }
}
