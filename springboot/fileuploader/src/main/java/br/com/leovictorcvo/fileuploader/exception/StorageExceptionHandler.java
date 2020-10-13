package br.com.leovictorcvo.fileuploader.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StorageExceptionHandler {
	@ExceptionHandler(StorageException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handle(StorageException exc) {
		exc.printStackTrace();
		return ResponseEntity.badRequest().body(exc.getMessage());
	}
}
