package moda.praia.controller.validator;

import moda.praia.controller.form.FormProduto;
import moda.praia.modulo.produtos.bean.Produto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProdutoFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FormProduto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		FormProduto formProduto = (FormProduto) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "NotEmpty.produtoForm.descricao");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoria", "NotEmpty.produtoForm.categoria");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subcategoria", "NotEmpty.produtoForm.subcategoria");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoMedida", "NotEmpty.produtoForm.tipoMedida");
		
		if(formProduto != null){
			if(formProduto.getValor() == null || formProduto.getValor().doubleValue() < 0){
				errors.rejectValue("valor", "NotEmpty.produtoForm.valor");
			}
			
		}
	
	}

}
