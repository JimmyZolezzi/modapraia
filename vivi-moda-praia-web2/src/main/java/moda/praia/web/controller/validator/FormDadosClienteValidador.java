package moda.praia.web.controller.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import moda.praia.uteis.CPFValidator;
import moda.praia.uteis.Validacoes;
import moda.praia.web.controller.form.FormDadosCliente;

@Component
public class FormDadosClienteValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FormDadosCliente.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		
		FormDadosCliente formDadosCliente = (FormDadosCliente) object;
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeCompleto", "NotEmpty.formDadosCliente.nomeCompleto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rg", "NotEmpty.formDadosCliente.rg");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "NotEmpty.formDadosCliente.cpf");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascimento", "NotEmpty.formDadosCliente.dataNascimento");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefone", "NotEmpty.formDadosCliente.telefone");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.formDadosCliente.email");
		
		if(formDadosCliente != null){
			//valida telefone
			String telefone = formDadosCliente.getTelefone();
			if(telefone != null && !telefone.equals("") && !Validacoes.validarNumeroTelefone(telefone)){
				errors.rejectValue("telefone", "NotValid.formDadosCliente.telefone");
			}
			//Valida Celular
			String celular = formDadosCliente.getCelular();
			if(celular != null && !celular.equals("") && !Validacoes.validarNumeroCelular(celular)){
				errors.rejectValue("celular", "NotValid.formDadosCliente.celular");
			}
			//Valida email
			String email = formDadosCliente.getEmail();
			if(email != null && !email.equals("") && !Validacoes.validaEmail(email)){
				errors.rejectValue("email", "NotValid.formDadosCliente.email");
			}
			// Valida CPF
			String cpf  = formDadosCliente.getCpf();
			if(cpf != null && !cpf.equals("") && !CPFValidator.validaCPF(cpf)){
				errors.rejectValue("cpf", "NotValid.formDadosCliente.cpf");
			}
		}
		
		
		
	}

}
