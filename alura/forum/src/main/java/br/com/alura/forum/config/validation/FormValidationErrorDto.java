package br.com.alura.forum.config.validation;

public class FormValidationErrorDto {
	private String campo;
	private String erro;
	
	public FormValidationErrorDto(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	
}
