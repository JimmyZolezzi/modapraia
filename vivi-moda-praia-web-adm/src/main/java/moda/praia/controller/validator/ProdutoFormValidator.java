package moda.praia.controller.validator;

import moda.praia.controller.form.FormProduto;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.uteis.Valores;

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
		
		if(formProduto != null){
			
			String pesoStr = Valores.desformataMoeda(formProduto.getPesoStr());
			String larguraStr = Valores.desformataMoeda(formProduto.getLarguraStr());
			String alturaStr = Valores.desformataMoeda(formProduto.getAlturaStr());
			String comprimentoStr = Valores.desformataMoeda(formProduto.getComprimentoStr());
			String valorStr = Valores.desformataMoeda(formProduto.getValorStr());
			//Peso
			if(!Valores.isValor(pesoStr)){
				errors.rejectValue("pesoStr", "invalid.produtoForm.peso");
			}
			//Largura
			if(!Valores.isValor(larguraStr)){
				errors.rejectValue("larguraStr", "invalid.produtoForm.largura");
			}
			//Altura
			if(!Valores.isValor(alturaStr)){
				errors.rejectValue("alturaStr", "invalid.produtoForm.altura");
			}
			//Comprimento
			if(!Valores.isValor(comprimentoStr)){
				errors.rejectValue("comprimentoStr", "invalid.produtoForm.comprimento");
			}
			//Valor
			if(!Valores.isValor(valorStr)){
				errors.rejectValue("valorStr", "invalid.produtoForm.valor");
			}
		}
	
	}

}
