package com.ad.infrastructure.adapter.in.web;

import com.ad.application.service.ListarAnunciante;
import com.ad.domain.model.Anunciante;
import com.ad.application.service.Cadastrar;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anunciantes")
public class AnuncianteController {
    private final Cadastrar cadastrar;
    private final ListarAnunciante listarAnunciante;

    public AnuncianteController(Cadastrar cadastrar, ListarAnunciante listarAnunciante) {
        this.cadastrar = cadastrar;
        this.listarAnunciante = listarAnunciante;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anunciante cadastrar(@Valid @RequestBody CadastrarAnuncianteRequest request) {
        return cadastrar.executar(request.nome(), request.email(), request.cnpj());
    }

    @GetMapping
    public List<Anunciante> listarTodos() {
        return listarAnunciante.executar();
    }
}