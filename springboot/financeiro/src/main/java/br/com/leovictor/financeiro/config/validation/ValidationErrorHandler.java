package br.com.leovictor.financeiro.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.leovictor.financeiro.dto.ErroValidacaoDTO;

@RestControllerAdvice
public class ValidationErrorHandler {
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<ErroValidacaoDTO> handle(MethodArgumentNotValidException exception) {
		List<ErroValidacaoDTO> listaDeErros = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroValidacaoDTO erro = new ErroValidacaoDTO(e.getField(), mensagem);
			listaDeErros.add(erro);
		});
		return listaDeErros;
	}
}
