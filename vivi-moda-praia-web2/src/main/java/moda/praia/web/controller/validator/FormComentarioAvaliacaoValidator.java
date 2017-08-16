package moda.praia.web.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import moda.praia.modulo.avaliacao.bean.Avaliacao;
import moda.praia.web.controller.form.FormComentarioAvaliacao;

@Component
public class FormComentarioAvaliacaoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return FormComentarioAvaliacaoValidator.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		FormComentarioAvaliacao formComentarioAvaliacao = (FormComentarioAvaliacao) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "avaliacao.titulo", "NotEmpty.formComentarioAvaliacao.titulo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "avaliacao.nota", "NotEmpty.formComentarioAvaliacao.nota");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "avaliacao.comentario", "NotEmpty.formComentarioAvaliacao.comentario");
		if(formComentarioAvaliacao != null && formComentarioAvaliacao.getAvaliacao() !=null){
			Avaliacao avaliacao = formComentarioAvaliacao.getAvaliacao();
			if(avaliacao.getNota() == 0){
				errors.rejectValue("avaliacao.nota", "NotEmpty.formComentarioAvaliacao.nota");
			}
		}
		
	}

}
