package com.udemy.expert.exercicios.controller;

import com.udemy.expert.exercicios.dto.ApiErrors;
import com.udemy.expert.exercicios.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors hanndleRegraNegocioException(RegraNegocioException ex){
        String mensagemError = ex.getMessage();
        return new ApiErrors(mensagemError);
    }

}
