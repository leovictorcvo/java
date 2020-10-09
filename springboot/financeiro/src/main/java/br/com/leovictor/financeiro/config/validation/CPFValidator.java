package br.com.leovictor.financeiro.config.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.leovictor.financeiro.domain.CPF;

public class CPFValidator implements ConstraintValidator<CPFValid, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		try {
			new CPF(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
