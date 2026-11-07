package com.ad.application.service;

import com.ad.application.port.out.BuscarAnunciantePorIdPort;
import com.ad.domain.exception.AnuncianteNaoEncontradoExc;
import com.ad.domain.model.Anunciante;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarAnunciantePorIdTest {

    @Mock
    private BuscarAnunciantePorIdPort buscarAnunciantePorIdPort;

    @InjectMocks
    private BuscarAnunciantePorId buscarAnunciantePorIdService;

    @Test
    void deveRetornarAnuncianteEncontrado(){
        Anunciante anunciante = new Anunciante(1L,"Empresa", "contato@empresa.com", "12345678000100");
        when(buscarAnunciantePorIdPort.buscarPorId(1L)).thenReturn(Optional.of(anunciante));
        Anunciante anuncianteEncontrado = buscarAnunciantePorIdService.executar(1L);
        assertSame(anunciante, anuncianteEncontrado);
        verify(buscarAnunciantePorIdPort).buscarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoAnuncianteNaoEncontrado(){
        when(buscarAnunciantePorIdPort.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(AnuncianteNaoEncontradoExc.class, () -> buscarAnunciantePorIdService.executar(1L));
        verify(buscarAnunciantePorIdPort).buscarPorId(1L);
    }
}