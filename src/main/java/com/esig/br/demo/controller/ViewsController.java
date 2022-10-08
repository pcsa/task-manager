package com.esig.br.demo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewsController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/tarefa-list.jsf");
        registry.addViewController("/nova-tarefa") .setViewName("forward:/tarefa-form.jsf");
        registry.addViewController("/error") .setViewName("forward:/tarefa-list.jsf");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        }

}
