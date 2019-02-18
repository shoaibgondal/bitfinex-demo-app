/**
 *
 */
package com.example.bitfinex.demo.controller;

import com.example.bitfinex.demo.dto.ErrorResponseDto;
import com.example.bitfinex.demo.dto.ResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 *
 *
 */
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

    Logger logger = LogManager.getLogger(GlobalExceptionHandlingControllerAdvice.class);

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* . . . . . . . . . . . . . EXCEPTION HANDLERS . . . . . . . . . . . . . . */
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /**
     * Convert a predefined exception to an HTTP Status code
     */
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
    // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {
        logger.error("Request raised a DataIntegrityViolationException");
        // Nothing to do
    }

    /**
     * Convert a predefined exception to an HTTP Status code and specify the
     * name of a specific view that will be used to display the error.
     *
     * @return Exception view.
     */
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError(Exception exception) {
        // Nothing to do. Return value 'databaseError' used as logical view name
        // of an error page, passed to view-resolver(s) in usual way.
        logger.error("Request raised " + exception.getClass().getSimpleName());
        return "databaseError";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseDto processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ResponseDto responseDto = new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), "Invalid Request Params",
                processFieldError(result.getFieldErrors()));
        return responseDto;
    }

    private List<String> processFieldError(List<FieldError> list) {
        Locale locale = new Locale("en");

        return list.stream().peek(e -> logger.debug("{}:{} -> {}:{}", e.getObjectName(),
                e.getField(), e.getDefaultMessage(), e.getRejectedValue()))
                .map(e -> e.getDefaultMessage()).collect(Collectors.toList());
    }
}
