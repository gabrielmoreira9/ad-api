package com.ad.application.port.out;

import com.ad.domain.model.Anunciante;
import java.util.Optional;

public interface BuscarAnunciantePorIdPort {
    Optional<Anunciante> buscarPorId(Long id);
}