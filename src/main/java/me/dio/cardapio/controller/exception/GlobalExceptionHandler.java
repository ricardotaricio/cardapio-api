package me.dio.cardapio.controller.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import me.dio.cardapio.service.exception.BusinessException;
import me.dio.cardapio.service.exception.ResourceNotFoundException;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler { //extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		return createProblem(ex, HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ProblemDetail handleBusinessException(BusinessException ex, WebRequest request) {
		return createProblem(ex, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		logException(ex);
		
		Map<String, Object> errors = new HashMap<>();
		Map<String, Object> property = new HashMap<String, Object>();

	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });

	    property.put("errors", errors);
	    
	    ex.getBody().setProperties(property);

		return ex.getBody();
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
		return createProblem(ex, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ProblemDetail handleThrowableException(Throwable ex, WebRequest request) {
		return createProblem(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	private ProblemDetail createProblem(Throwable ex, HttpStatus httpStatus, WebRequest request) {
		logException(ex);

		ProblemDetail problem = ProblemDetail.forStatusAndDetail(
				httpStatus,
				ex.getLocalizedMessage());

		return problem;
	}
	
	private void logException(Throwable ex) {
		logger.error("Exception: ", ex);
	}
}
