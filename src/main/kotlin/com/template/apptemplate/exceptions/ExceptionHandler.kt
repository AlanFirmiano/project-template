package com.template.apptemplate.exceptions

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(e: NotFoundException?): ResponseEntity<ErrorData?>? {
        val exceptionResponse = ErrorData("Not found", emptySet())
        return ResponseEntity<ErrorData?>(exceptionResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(FieldNotFoundException::class)
    fun handleFieldNotFoundException(ex: FieldNotFoundException): ResponseEntity<ExceptionMessage?>? {
        val errorMessage = ExceptionMessage("field not found", ex.message)
        return ResponseEntity(errorMessage, HttpStatus.PRECONDITION_FAILED)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ExceptionMessage> {
        val errorMessage = ExceptionMessage("internal error", "INTERNAL SERVER ERROR")
        return ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException): ResponseEntity<InvalidFieldMessage> {
        val errorMessage = InvalidFieldMessage("invalid fields", "Invalid fields",
                ex.bindingResult.fieldErrors.stream().map {
                    it: FieldError -> FieldErrorMessage(it.field, it.defaultMessage)
                }.toList())
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun handleMissing(ex: MissingKotlinParameterException): ResponseEntity<Any> {
        val errorMessage = InvalidFieldMessage("invalid field", "Invalid field: ${ex.parameter.name}")
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleExceptionNotReadable(ex: HttpMessageNotReadableException): ResponseEntity<ExceptionMessage> {
//        val errorMessage = ExceptionMessage("bad request", "JSON parse error")
//
//        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
//
        val cause = ex.cause

        val errorMessage = when (cause) {
            is JsonMappingException -> {
                val fieldName = cause.path.map { it.fieldName }.joinToString(".")
                ExceptionMessage("bad request", "JSON parse error: Invalid value for field '$fieldName'")
            }
            else -> ExceptionMessage("bad request", "JSON parse error")
        }

        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleExceptionMethodNotSupportedException(ex: HttpRequestMethodNotSupportedException): ResponseEntity<ExceptionMessage> {
        val errorMessage = ExceptionMessage("bad request", "Bad Request")
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}