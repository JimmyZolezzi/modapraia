package moda.praia.web.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import moda.praia.web.controller.form.FormEnderecoCliente;
import moda.praia.web.controller.form.FormLogin;

@Component
public class LoginFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FormLogin.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		FormLogin formLogin = (FormLogin) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.formLogin.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "NotEmpty.formLogin.senha");
		
	}

}
