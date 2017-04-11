package moda.praia.web.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.uteis.CPFValidator;
import moda.praia.uteis.Validacoes;
import moda.praia.web.controller.form.FormCliente;

/**
 * Validação de Cliente Fisico
 * @author ledzo
 *
 */
@Component
public class ClienteFisicoFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FormCliente.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		FormCliente formCliente = (FormCliente) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente.nome", "NotEmpty.formCliente.nome");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente.rg", "NotEmpty.formCliente.rg");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente.dataNascimento", "NotEmpty.formCliente.dataNascimento");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente.cpfCnpj", "NotEmpty.formCliente.cpf");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente.senha", "NotEmpty.formCliente.senha");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmacaoSenha", "NotEmpty.formCliente.confirmacaoSenha");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente.telefone", "NotEmpty.formCliente.telefone");
		
		if(formCliente != null && formCliente.getCliente() != null){
			Cliente cliente = formCliente.getCliente();
			
			//Valida CPF
			if(cliente.getCpfCnpj() !=null && !cliente.getCpfCnpj().equals("")){
				String cpf = cliente.getCpfCnpj();
				if(!CPFValidator.validaCPF(cpf)){
					errors.rejectValue("cliente.cpfCnpj", "NotValid.FormCliente.cpf");
				}
			}
			//Valida numero de telefone
			if(cliente.getTelefone() != null && !cliente.getTelefone().equals("")){
				String telefone = cliente.getTelefone();
				if(!Validacoes.validarNumeroTelefone(telefone)){
					errors.rejectValue("cliente.telefone", "NotValid.FormCliente.telefone");
				}
			}
			//Valida numero de celular
			if(cliente.getCelular() != null && !cliente.getCelular().equals("")){
				String celular = cliente.getCelular();
				if(!Validacoes.validarNumeroCelular(celular)){
					errors.rejectValue("cliente.celular", "NotValid.FormCliente.celular");
				}
			}
			
			if(cliente.getSenha() != null && !Validacoes.validaSenha(cliente.getSenha())){
				errors.rejectValue("confirmacaoSenha", "NotValid.FormCliente.senha");
			}
			
			if(cliente.getSenha() != null && !cliente.getSenha().isEmpty() && formCliente.getConfirmacaoSenha() != null){
				String senha = cliente.getSenha();
				String confirmacaoSenha  = formCliente.getConfirmacaoSenha();
				if(!senha.equals(confirmacaoSenha)){
					errors.rejectValue("cliente.senha", "NotValid.FormCliente.confirmacaoSenha");
				}
			}
			
		}
		

		
	}

}
