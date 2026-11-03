package com.ad.infrastructure.adapter.in.web;

import com.ad.domain.model.Anunciante;
import com.ad.application.service.Cadastrar;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anunciantes")
public class AnuncianteController {
    private final Cadastrar cadastrar;

    public AnuncianteController(Cadastrar cadastrar) {
        this.cadastrar = cadastrar;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anunciante cadastrar(@RequestBody CadastrarAnuncianteRequest request) {
        return cadastrar.executar(request.nome(), request.email(), request.cnpj());
    }
}