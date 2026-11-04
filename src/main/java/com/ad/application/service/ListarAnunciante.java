package com.ad.application.service;

import com.ad.application.port.out.ListarAnunciantePort;
import com.ad.domain.model.Anunciante;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAnunciante {

    private final ListarAnunciantePort listarAnunciantePort;

    public ListarAnunciante(ListarAnunciantePort listarAnunciantePort) {
        this.listarAnunciantePort = listarAnunciantePort;
    }

    public List<Anunciante> executar() {
        return listarAnunciantePort.listarTodos();
    }
}
