package com.ad.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "anunciantes")
public class AnuncianteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cnpj;

    public AnuncianteEntity() {
    }

    public AnuncianteEntity(String nome, String email, String cnpj) {
        this.nome = nome;
        this.email = email;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
