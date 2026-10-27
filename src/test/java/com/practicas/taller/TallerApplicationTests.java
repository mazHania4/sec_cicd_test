package com.practicas.taller;

import com.practicas.taller.controller.AuthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Pruebas unitarias mínimas, para validar flujo del pipeline, no cubrir todos los casos.
 */
@SpringBootTest
class TallerApplicationTests {

    @Autowired
    private AuthController authController;

    @Test
    void contextLoads() {
        // Verifica que el contexto de Spring levante correctamente,
    }

    @Test
    void elHashMd5TieneLaLongitudEsperada() {
        String resultado = authController.hash("claveDePrueba123");
        String hash = resultado.substring(resultado.lastIndexOf('=') + 2);
        assertThat(hash).hasSize(32);
    }

}
