package com.practicas.taller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Vulnerabilidad: PATH TRAVERSAL
 * El parámetro "fileName" se concatena directamente a una ruta base sin sanitización.
 * Un atacante puede usar secuencias "../" para escapar del directorio
 */
@RestController
public class FileController {

    private static final Path BASE_DIR = Paths.get("/tmp/").toAbsolutePath().normalize();

    @GetMapping("/api/files/download")
    public String download(@RequestParam String fileName) {
        try {
            // 1. Resolver y normalizar la ruta solicitada contra el directorio base.
            Path requested = BASE_DIR.resolve(fileName).normalize();

            // 2. Verificar que la ruta normalizada siga dentro del directorio base.
            if (!requested.startsWith(BASE_DIR)) {
                return "Acceso denegado: ruta fuera del directorio permitido.";
            }

            if (!Files.exists(requested) || !Files.isRegularFile(requested)) {
                return "Archivo no encontrado.";
            }

            byte[] contenido = Files.readAllBytes(requested);
            return new String(contenido);

        } catch (IOException e) {
            // No se expone el detalle interno de la excepcion al cliente.
            return "No fue posible leer el archivo solicitado.";
        }
    }
}
