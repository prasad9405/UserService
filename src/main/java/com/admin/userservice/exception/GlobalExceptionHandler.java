package com.admin.userservice.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);



	@ExceptionHandler(value = UsersNotFoundException.class)
	public ResponseEntity<?> handleUsersNotFoundException(UsersNotFoundException exception) {
		logger.error(exception.getMessage(), exception);
		ErrorResponse msg = new ErrorResponse(exception.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = DataNotFoundException.class)
	public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException exception) {
		logger.error(exception.getMessage(), exception);
		ErrorResponse msg = new ErrorResponse(exception.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(value=BadCredentialsException.class)
	public ResponseEntity<?>badCredentials(BadCredentialsException exception){
		logger.error(exception.getMessage(),exception);
		ErrorResponse msg=new ErrorResponse(exception.getMessage(),LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value=PasswordNotMatchException.class)
	public ResponseEntity<?>passwordNotMatch(PasswordNotMatchException exception) {
		logger.error(exception.getMessage(), exception);
		ErrorResponse msg = new ErrorResponse(exception.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	}

}
