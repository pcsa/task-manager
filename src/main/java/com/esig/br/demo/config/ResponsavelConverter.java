package com.esig.br.demo.config;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.esig.br.demo.domain.model.Responsavel;

@FacesConverter(forClass=Responsavel.class)
public class ResponsavelConverter implements Converter<Object>{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return new Responsavel(null, value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Responsavel responsavel = (Responsavel) value;
        return responsavel.getNome();
    }
}
