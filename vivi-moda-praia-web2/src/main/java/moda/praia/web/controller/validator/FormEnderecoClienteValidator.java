package moda.praia.web.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import moda.praia.web.controller.form.FormEnderecoCliente;

@Component
public class FormEnderecoClienteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FormEnderecoCliente.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {

		FormEnderecoCliente formEnderecoCliente = (FormEnderecoCliente) object;
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.endereco", "NotEmpty.formEnderecoCliente.endereco");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.cep", "NotEmpty.formEnderecoCliente.cep");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.cidade", "NotEmpty.formEnderecoCliente.cidade");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.estado", "NotEmpty.formEnderecoCliente.estado");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.bairro", "NotEmpty.formEnderecoCliente.bairro");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.numero", "NotEmpty.formEnderecoCliente.numero");
		
		if(formEnderecoCliente != null){
			
		}
		
	}

}
