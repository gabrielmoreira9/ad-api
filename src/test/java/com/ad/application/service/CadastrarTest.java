package com.ad.application.service;

import com.ad.application.port.out.SalvarAnunciantePort;
import com.ad.domain.model.Anunciante;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CadastrarTest {

    @Mock
    private SalvarAnunciantePort salvarAnunciantePort;

    @InjectMocks
    private Cadastrar cadastrar;

    @Test
    void deveSalvarAnunciante() {
        Anunciante anuncianteSalvo = new Anunciante("Empresa", "contato@empresa.com", "12345678000100");
        when(salvarAnunciantePort.salvar(any(Anunciante.class)))
                .thenReturn(anuncianteSalvo);

        Anunciante resultado = cadastrar.executar("Empresa", "contato@empresa.com", "12345678000100");

        assertSame(anuncianteSalvo, resultado);
        ArgumentCaptor <Anunciante> captor = ArgumentCaptor.forClass(Anunciante.class);
        verify(salvarAnunciantePort).salvar(captor.capture());

        Anunciante enviado = captor.getValue();
        assertEquals("Empresa", enviado.getNome());
        assertEquals("contato@empresa.com", enviado.getEmail());
        assertEquals("12345678000100", enviado.getCnpj());
    }

    @Test
    void deveColocarErroQuandoFalhaAoSalvarAnunciante() {
        RuntimeException erro = new RuntimeException("Falha ao salvar anunciante");
        when(salvarAnunciantePort.salvar(any(Anunciante.class))).thenThrow(erro);
        RuntimeException resultado = assertThrows(RuntimeException.class, () -> cadastrar.executar("Empresa", "contato@empresa.com", "12345678000100"));
    }
}