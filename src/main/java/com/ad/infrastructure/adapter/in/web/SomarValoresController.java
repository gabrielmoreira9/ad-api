package com.ad.infrastructure.adapter.in.web;

import com.ad.application.service.SomarValoresService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculadora")
public class SomarValoresController {

    private final SomarValoresService somarValoresService;

    public SomarValoresController(SomarValoresService somarValoresService) {
        this.somarValoresService = somarValoresService;
    }

    @GetMapping("/somar")
    public int somar(
            @RequestParam int a,
            @RequestParam int b
    ) {
        return somarValoresService.somar(a, b);
    }
}
