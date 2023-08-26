package com.example.repodownloader.controller

import com.example.repodownloader.model.exception.ErrorMessage
import com.example.repodownloader.model.exception.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {
    @ExceptionHandler(HttpMediaTypeNotAcceptableException::class)
    fun handleMediaTypeException(e: HttpMediaTypeNotAcceptableException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            HttpStatus.NOT_ACCEPTABLE.value(),
            e.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_ACCEPTABLE)
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(e: UserNotFoundException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            e.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }
}