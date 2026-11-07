package com.ad.infrastructure.adapter.out.persistence;

import com.ad.TestcontainersConfiguration;
import com.ad.domain.model.Anunciante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Import({
        TestcontainersConfiguration.class,
        AnunciantePersistenceAdapter.class
})
class AnunciantePersistenceAdapterTest {

    private long anuncianteBaseId;

    @Autowired
    private AnunciantePersistenceAdapter adapter;

    @Autowired
    private AnuncianteRepository repository;

    @BeforeEach
    void prepararBanco() {
        repository.deleteAll();

        AnuncianteEntity anuncianteSalvo = repository.save(
                new AnuncianteEntity(
                        "Empresa Base",
                        "base@empresa.com",
                        "12345678000100"
                )
        );

        anuncianteBaseId = anuncianteSalvo.getId();
    }

    @Test
    void deveSalvarAnuncianteNoPostgres() {

        Anunciante anunciante = new Anunciante(
                "Nova Empresa",
                "contato@novaempresa.com",
                "98765432000100"
        );

        Anunciante resultado = adapter.salvar(anunciante);

        assertNotNull(resultado.getId());

        AnuncianteEntity encontrado = repository
                .findById(resultado.getId())
                .orElseThrow();

        assertEquals("Nova Empresa", encontrado.getNome());
        assertEquals("contato@novaempresa.com", encontrado.getEmail());
        assertEquals("98765432000100", encontrado.getCnpj());
    }

    @Test
    void deveListarTodosOsAnunciantes(){
        List<Anunciante> resultado = adapter.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Empresa Base", resultado.get(0).getNome());
        assertEquals("base@empresa.com", resultado.get(0).getEmail());
    }

    @Test
    void deveBuscarAnunciantePorId(){
        Anunciante resultado = adapter.buscarPorId(anuncianteBaseId).orElseThrow();

        assertEquals(anuncianteBaseId, resultado.getId());
        assertEquals("Empresa Base", resultado.getNome());
    }

    @Test
    void deveRetornarVazioQuandoNaoEncontrarAnunciante(){

        assertTrue(adapter.buscarPorId(-1L).isEmpty());
    }
}