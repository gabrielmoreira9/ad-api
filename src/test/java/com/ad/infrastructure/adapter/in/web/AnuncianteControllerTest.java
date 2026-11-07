package com.ad.infrastructure.adapter.in.web;

import com.ad.application.service.BuscarAnunciantePorId;
import com.ad.application.service.Cadastrar;
import com.ad.application.service.ListarAnunciante;
import com.ad.domain.model.Anunciante;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnuncianteController.class)
class AnuncianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private Cadastrar cadastrar;

    @MockitoBean
    private ListarAnunciante listarAnunciante;

    @MockitoBean
    private BuscarAnunciantePorId buscarAnunciantePorId;

    @Test
    void deveCadastrarAnunciante() throws Exception {

        Anunciante anunciante = new Anunciante(
                1L,
                "Empresa",
                "contato@empresa.com",
                "12345678000100"
        );

        when(cadastrar.executar(
                "Empresa",
                "contato@empresa.com",
                "12345678000100"
        )).thenReturn(anunciante);

        mockMvc.perform(post("/anunciantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Empresa",
                                    "email": "contato@empresa.com",
                                    "cnpj": "12345678000100"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Empresa"))
                .andExpect(jsonPath("$.email").value("contato@empresa.com"));
    }

    @Test
    void deveListarTodosOsAnunciantes() throws Exception {

        List<Anunciante> anunciantes = List.of(
                new Anunciante(
                        1L,
                        "Empresa",
                        "contato@empresa.com",
                        "12345678000100"
                )
        );

        when(listarAnunciante.executar()).thenReturn(anunciantes);

        mockMvc.perform(get("/anunciantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nome").value("Empresa"));
    }

    @Test
    void deveBuscarAnunciantePorId() throws Exception {

        Anunciante anunciante = new Anunciante(
                1L,
                "Empresa",
                "contato@empresa.com",
                "12345678000100"
        );

        when(buscarAnunciantePorId.executar(1L))
                .thenReturn(anunciante);

        mockMvc.perform(get("/anunciantes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Empresa"));
    }
}