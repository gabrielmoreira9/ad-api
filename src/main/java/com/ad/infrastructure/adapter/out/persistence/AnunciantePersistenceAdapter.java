package com.ad.infrastructure.adapter.out.persistence;

import com.ad.application.port.out.ListarAnunciantePort;
import com.ad.application.port.out.SalvarAnunciantePort;
import com.ad.application.port.out.BuscarAnunciantePorIdPort;
import com.ad.domain.model.Anunciante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AnunciantePersistenceAdapter implements SalvarAnunciantePort, ListarAnunciantePort, BuscarAnunciantePorIdPort {

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

    @Override
    public List<Anunciante> listarTodos() {

        return repository.findAll()
                .stream()
                .map(entity -> new Anunciante(
                        entity.getId(),
                        entity.getNome(),
                        entity.getEmail(),
                        entity.getCnpj()
                ))
                .toList();
    }

    @Override
    public Optional<Anunciante> buscarPorId(Long id) {

        return repository.findById(id)
                .map(entity -> new Anunciante(
                        entity.getId(),
                        entity.getNome(),
                        entity.getEmail(),
                        entity.getCnpj()
                ));
    }
}