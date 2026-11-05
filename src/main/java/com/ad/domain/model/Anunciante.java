package com.ad.domain.model;

import com.ad.domain.exception.AnuncianteInvalidoExc;

public class Anunciante {

    private Long id;
    private String nome;
    private String email;
    private String cnpj;

    public Anunciante(String nome, String email, String cnpj) {

        if (nome == null || nome.isBlank()) {
            throw new AnuncianteInvalidoExc("Digite um nome");
        }

        if (cnpj == null || cnpj.isBlank()) {
            throw new AnuncianteInvalidoExc("Digite um CNPJ");
        }

        if (email == null || email.isBlank()) {
            throw new AnuncianteInvalidoExc("Digite um email");
        }

        this.nome = nome;
        this.email = email;
        this.cnpj = cnpj;
    }

    public Anunciante(Long id, String nome, String email, String cnpj) {
        this(nome, email, cnpj);
        this.id = id;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new AnuncianteInvalidoExc("Digite um nome");
        }

        this.nome = nome;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new AnuncianteInvalidoExc("Digite um email");
        }

        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCnpj() {
        return cnpj;
    }
}