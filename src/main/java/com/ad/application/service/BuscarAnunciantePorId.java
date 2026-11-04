package com.ad.application.service;

import com.ad.application.port.out.BuscarAnunciantePorIdPort;
import com.ad.domain.exception.AnuncianteNaoEncontradoExc;
import com.ad.domain.model.Anunciante;
import org.springframework.stereotype.Service;

@Service
public class BuscarAnunciantePorId {

    private final BuscarAnunciantePorIdPort buscarAnunciantePorIdPort;

    public BuscarAnunciantePorId(
            BuscarAnunciantePorIdPort buscarAnunciantePorIdPort
    ) {
        this.buscarAnunciantePorIdPort = buscarAnunciantePorIdPort;
    }

    public Anunciante executar(Long id) {
        return buscarAnunciantePorIdPort.buscarPorId(id).orElseThrow(() -> new AnuncianteNaoEncontradoExc(id));
    }
}