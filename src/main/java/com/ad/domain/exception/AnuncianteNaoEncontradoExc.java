package com.ad.domain.exception;

public class AnuncianteNaoEncontradoExc extends RuntimeException{
    public AnuncianteNaoEncontradoExc(Long id) {
        super("Anunciante não encontrado: " + id);
    }
}
