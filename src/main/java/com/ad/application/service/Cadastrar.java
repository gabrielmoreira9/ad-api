package com.ad.application.service;

import com.ad.application.port.out.SalvarAnunciantePort;
import com.ad.domain.model.Anunciante;

public class Cadastrar{

    private final SalvarAnunciantePort salvarAnunciantePort;

    public Cadastrar(SalvarAnunciantePort salvarAnunciantePort) {
        this.salvarAnunciantePort = salvarAnunciantePort;
    }

    public Anunciante executar(String nome, String email, String cnpj) {

        Anunciante anunciante = new Anunciante(nome, email, cnpj);

        return salvarAnunciantePort.salvar(anunciante);
    }
}
