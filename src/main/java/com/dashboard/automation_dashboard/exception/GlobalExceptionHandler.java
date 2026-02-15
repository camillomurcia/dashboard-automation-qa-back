package com.dashboard.automation_dashboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(RecursoNoEncontradoException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", System.currentTimeMillis());
        respuesta.put("status", 404);
        respuesta.put("error", "Not Found");
        respuesta.put("message", ex.getMessage()); // Aquí irá tu mensaje: "Funcionalidad no encontrada"
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

}
