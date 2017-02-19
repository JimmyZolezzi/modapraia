package moda.praia.controller.validator;

import moda.praia.modulo.produtos.bean.Subcategoria;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SubCategoriaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Subcategoria.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Subcategoria subcategoria = (Subcategoria) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "NotEmpty.subcategoriaForm.descricao");
		if(subcategoria != null){

			if(subcategoria.getCategoria()==null || subcategoria.getCategoria().getId() == 0){
				errors.rejectValue("categoria", "NotEmpty.subcategoriaForm.categoria");
			}
			
		}
	}

}
