package com.practicas.taller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Vulnerabilidad: USO DE ALGORITMO CRIPTOGRAFICO OBSOLETO
 * MD5 está roto: fácil hacer fuerza bruta, tablas rainbow y tiene colisiones conocidas.
 */
@RestController
public class AuthController {

    @GetMapping("/api/auth/hash")
    public String hash(@RequestParam String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return "MD5(" + password + ") = " + sb;

        } catch (NoSuchAlgorithmException e) {
            return "Error al calcular el hash: " + e.getMessage();
        }
    }
}
