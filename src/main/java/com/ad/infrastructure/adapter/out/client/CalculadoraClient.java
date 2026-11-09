package com.ad.infrastructure.adapter.out.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "calculadora-api",
        url = "${calculadora-api.url}"
)
public interface CalculadoraClient {

    @GetMapping("/calculadora/somar")
    int somar(
            @RequestParam("a") int a,
            @RequestParam("b") int b
    );
}