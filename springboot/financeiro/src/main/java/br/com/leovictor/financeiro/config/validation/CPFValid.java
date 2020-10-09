package br.com.leovictor.financeiro.config.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPFValidator.class)
public @interface CPFValid {
	String message() default "CPF inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value() default "";

}
