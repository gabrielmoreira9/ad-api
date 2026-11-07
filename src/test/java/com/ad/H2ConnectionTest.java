package com.ad;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class H2ConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void deveConectarAoBancoH2() throws Exception {
        try (Connection conexao = dataSource.getConnection()) {
            assertTrue(conexao.getMetaData().getURL().startsWith("jdbc:h2:mem:"));
        }
    }
}
