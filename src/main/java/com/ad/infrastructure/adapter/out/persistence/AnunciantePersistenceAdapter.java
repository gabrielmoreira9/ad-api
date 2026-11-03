package com.ad.infrastructure.adapter.out.persistence;

import com.ad.application.port.out.SalvarAnunciantePort;
import com.ad.domain.model.Anunciante;
import org.springframework.stereotype.Component;

@Component
public class AnunciantePersistenceAdapter implements SalvarAnunciantePort {

    private final AnuncianteRepository repository;

    public AnunciantePersistenceAdapter(AnuncianteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Anunciante salvar(Anunciante anunciante) {

        AnuncianteEntity entity = new AnuncianteEntity(
                anunciante.getNome(),
                anunciante.getEmail(),
                anunciante.getCnpj()
        );

        AnuncianteEntity salva = repository.save(entity);

        return new Anunciante(
                salva.getId(),
                salva.getNome(),
                salva.getEmail(),
                salva.getCnpj()
        );
    }
}