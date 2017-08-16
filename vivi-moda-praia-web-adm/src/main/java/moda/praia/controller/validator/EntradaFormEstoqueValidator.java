package moda.praia.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import moda.praia.controller.form.FormEntradaEstoque;

@Component
public class EntradaFormEstoqueValidator implements Validator  {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FormEntradaEstoque.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		FormEntradaEstoque formEntradaEstoque = (FormEntradaEstoque) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantidadeEntrada", "NotEmpty.formEntradaEstoque.quantidadeEntrada");
		if(formEntradaEstoque != null){
			if(formEntradaEstoque.getQuantidadeEntrada() <= 0){
				errors.rejectValue("quantidadeEntrada", "MustBeGreatThanZero.formEntradaEstoque.quantidadeEntrada");
			}
			
		}
		
	}

}
