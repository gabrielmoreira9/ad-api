package com.ad.infrastructure.adapter.in.web;

import com.ad.application.service.Cadastrar;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anunciante")
public class AnuncianteController {
    private final Cadastrar cadastrar;

    public AnuncianteController(Cadastrar cadastrar) {
        this.cadastrar = cadastrar;
    }
}