package br.com.alura.forum.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FormValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormValidationErrorDto> handle(MethodArgumentNotValidException exception) {
		List<FormValidationErrorDto> errors = new ArrayList<>();
		exception.getBindingResult().getFieldErrors().forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			errors.add(new FormValidationErrorDto(e.getField(), message));
		});
		return errors;
	}
}
