package com.ad.application.service;

import com.ad.application.port.out.SomarValoresPort;
import org.springframework.stereotype.Service;

@Service
public class SomarValoresService {
    private final SomarValoresPort somarValoresPort;

    public SomarValoresService(SomarValoresPort somarValoresPort) {
        this.somarValoresPort = somarValoresPort;
    }

    public int somar(int a, int b) {
        return somarValoresPort.somar(a, b);
    }
}
