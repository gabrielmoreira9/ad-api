package com.ad.infrastructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ad.domain.exception.AnuncianteNaoEncontradoExc;
import com.ad.domain.exception.AnuncianteInvalidoExc;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

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

    @ExceptionHandler(AnuncianteInvalidoExc.class)
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> tratarErroInesperado(Exception exception){
        logger.error("Erro inesperado", exception);
        ApiError erro = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro inesperado",
                "Ocorreu um erro. Tente novamente mais tarde."
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro);
    }
}