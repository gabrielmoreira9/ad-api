package com.ad.application.service;

import com.ad.application.port.out.ListarAnunciantePort;
import com.ad.domain.model.Anunciante;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarAnuncianteTest {

    @Mock
    private ListarAnunciantePort listarAnunciantePort;

    @InjectMocks
    private ListarAnunciante listarAnunciante;

    @Test
    void deveListarTodosOsAnunciantes() {
        List<Anunciante> anunciantes = List.of(
                new Anunciante("Empresa A", "a@empresa.com", "12345678000100"),
                new Anunciante("Empresa B", "b@empresa.com", "98765432000100")
        );

        when(listarAnunciantePort.listarTodos())
                .thenReturn(anunciantes);

        List<Anunciante> resultado = listarAnunciante.executar();

        assertSame(anunciantes, resultado);
        assertEquals(2, resultado.size());

        verify(listarAnunciantePort).listarTodos();
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremAnunciantes() {
        when(listarAnunciantePort.listarTodos())
                .thenReturn(List.of());

        List<Anunciante> resultado = listarAnunciante.executar();

        assertEquals(0, resultado.size());

        verify(listarAnunciantePort).listarTodos();
    }
}