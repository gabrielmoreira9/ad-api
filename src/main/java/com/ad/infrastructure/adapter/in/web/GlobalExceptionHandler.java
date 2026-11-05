package com.ad.infrastructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ad.domain.exception.AnuncianteNaoEncontradoExc;
import com.ad.domain.exception.AnuncianteInvalidoExc;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> tratarErroDeValidacao(
            MethodArgumentNotValidException exception
    ) {

        String mensagem = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ApiError erro = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                mensagem
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    @ExceptionHandler (AnuncianteInvalidoExc.class)
    public ResponseEntity<ApiError> tratarAnuncianteInvalido(AnuncianteInvalidoExc exception){
        ApiError erro = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Anunciante Inválido",
                exception.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    @ExceptionHandler(AnuncianteNaoEncontradoExc.class)
    public ResponseEntity<ApiError> tratarAnuncianteNaoEncontrado(AnuncianteNaoEncontradoExc exception){
        ApiError erro = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Anunciante não encontrado",
                exception.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erro);
    }
}