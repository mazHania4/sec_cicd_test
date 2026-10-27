package com.practicas.taller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Vulnerabilidad: PATH TRAVERSAL
 * El parámetro "fileName" se concatena directamente a una ruta base sin sanitización.
 * Un atacante puede usar secuencias "../" para escapar del directorio
 */
@RestController
public class FileController {

    private static final String BASE_DIR = "/tmp/";

    @GetMapping("/api/files/download")
    public String download(@RequestParam String fileName) {
        try {
            File file = new File(BASE_DIR + fileName);

            if (!file.exists()) {
                return "Archivo no encontrado: " + file.getAbsolutePath();
            }

            byte[] contenido = Files.readAllBytes(file.toPath());
            return new String(contenido);

        } catch (IOException e) {
            return "Error al leer el archivo: " + e.getMessage();
        }
    }
}
