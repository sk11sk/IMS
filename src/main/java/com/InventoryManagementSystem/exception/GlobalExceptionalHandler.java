package com.InventoryManagementSystem.exception;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.InventoryManagementSystem.bo.ProductBO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionalHandler extends ResponseEntityExceptionHandler {    // handling exception
	
static 	Logger logger = LoggerFactory.getLogger(ProductBO.class);


    @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
		HttpStatusCode status, WebRequest request) {
    	 Map<String, String> errors = new HashMap<>();
         ex.getBindingResult().getAllErrors().forEach(error -> {
             String fieldName = ((FieldError) error).getField();
             String errorMessage = error.getDefaultMessage();
             errors.put(fieldName, errorMessage);
         });

         logger.error("validation failed  :{}", errors);
         return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

}
	@ExceptionHandler
    public ResponseEntity<ExceptionResponse> resourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webrequest
    ){
    	ExceptionResponse errorDetails = new ExceptionResponse(new Date(), exception.getMessage());
    	logger.error("resource not found :{}", exception.getMessage());
     return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleGlobalException(
            Exception exception,
            WebRequest webrequest
    ){
    	ExceptionResponse errorDetails = new ExceptionResponse(new Date(), exception.getMessage());
    	logger.error("exception occured :{}", exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

