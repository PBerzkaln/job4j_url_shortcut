package ru.job4j.shortcut.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

/**
 * Аннотация @ControllerAdvice,
 * используемая совместно с @ExceptionHandler,
 * позволяет обрабатывать все исключения
 * (в данном случае - NullPointerException),
 * которые возникают во всех контроллерах.
 */
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private static final Logger LOG = LogManager.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(PSQLException e) {
        LOG.error(e.getMessage());
        return ResponseEntity.badRequest().body("Error: "
                + Objects.requireNonNull(e.getServerErrorMessage()).getMessage());
    }
}