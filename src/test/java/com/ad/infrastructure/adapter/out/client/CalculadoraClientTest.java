package com.ad.infrastructure.adapter.out.client;

import com.ad.AdApiApplication;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = AdApiApplication.class)
@ActiveProfiles("test")
class CalculadoraClientTest {

    @RegisterExtension
    static final WireMockExtension wireMock = WireMockExtension.newInstance()
            .options(com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig().dynamicPort())
            .build();

    @Autowired
    private CalculadoraClient calculadoraClient;

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("calculadora-api.url", () -> wireMock.baseUrl());
    }

    @Test
    void deveSomarValores() {
        wireMock.stubFor(get(urlPathEqualTo("/calculadora/somar"))
                .withQueryParam("a", equalTo("2"))
                .withQueryParam("b", equalTo("3"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("5")));

        assertEquals(5, calculadoraClient.somar(2, 3));
    }

    @Test
    void devePropagarErroDaCalculadora() {
        wireMock.stubFor(get(urlPathEqualTo("/calculadora/somar"))
                .willReturn(aResponse().withStatus(500)));

        assertThrows(Exception.class, () -> calculadoraClient.somar(2, 3));
    }
}
