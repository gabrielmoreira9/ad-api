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

    public AnuncianteEntity(){

    }
}
