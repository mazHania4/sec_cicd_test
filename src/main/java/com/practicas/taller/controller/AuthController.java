package com.practicas.taller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Vulnerabilidad: USO DE ALGORITMO CRIPTOGRAFICO OBSOLETO
 * MD5 está roto: fácil hacer fuerza bruta, tablas rainbow y tiene colisiones conocidas.
 */
@RestController
public class AuthController {

    private static final SecureRandom RANDOM = new SecureRandom();

    @GetMapping("/api/auth/hash")
    public String hash(@RequestParam String password) {
        try {
            byte[] salt = new byte[16];
            RANDOM.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] digest = md.digest(password.getBytes());

            String saltB64 = Base64.getEncoder().encodeToString(salt);
            String hashB64 = Base64.getEncoder().encodeToString(digest);

            return "SHA-256(salt + password) -> salt=" + saltB64 + " hash=" + hashB64;

        } catch (NoSuchAlgorithmException e) {
            return "Error al calcular el hash: " + e.getMessage();
        }
    }
}
