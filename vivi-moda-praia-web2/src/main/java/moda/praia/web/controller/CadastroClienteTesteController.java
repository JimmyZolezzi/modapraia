package moda.praia.web.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import moda.praia.web.controller.editor.CalendarddMMyyyyEditor;
import moda.praia.web.controller.form.FormCliente;
import moda.praia.web.controller.validator.ClienteFisicoFormValidator;

@Controller
@Transactional
public class CadastroClienteTesteController {

	@Autowired
	private ClienteFisicoFormValidator clienteFisicoFormValidator;
	@Autowired
	private CalendarddMMyyyyEditor calendarddMMyyyyEditor;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Calendar.class, calendarddMMyyyyEditor);
		binder.setValidator(clienteFisicoFormValidator);
	}
	
	@RequestMapping(value = "/cadastro-cliente-teste",method = RequestMethod.GET)
	public String carregarPaginaCadastroCliente(Model model){
		populateFormCliente(model);
		
		return "pages/cadastro-cliente-teste";
	}

	@RequestMapping(value = "/cadastrar/cliente-teste",method = RequestMethod.POST)
	public String cadastrarCliente(@ModelAttribute("formCliente")  FormCliente formCliente, Model model, BindingResult result){
		clienteFisicoFormValidator.validate(formCliente, result);
		model.addAttribute("status", result);
		if(!result.hasErrors()){
			
		}else{
			model.addAttribute("formCliente", formCliente);
		}
		
		
		return "pages/cadastro-cliente-teste";
	}
	private void populateFormCliente(Model model){
		
		FormCliente formCliente = new FormCliente();
		model.addAttribute("formCliente", formCliente);
	}
}
