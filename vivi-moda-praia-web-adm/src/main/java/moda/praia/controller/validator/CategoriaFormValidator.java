package moda.praia.controller.validator;

import moda.praia.modulo.produtos.bean.Categoria;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategoriaFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Categoria.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Categoria categoria = (Categoria) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "NotEmpty.categoriaForm.descricao");
		
	}

}
