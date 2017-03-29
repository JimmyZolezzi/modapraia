package moda.praia.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

import moda.praia.modulo.jsonview.Views.Public;
import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.Produto;

@Controller
@Transactional
public class PesquisaProdutoController {
	
	@Autowired
	private ProdutoBusiness produtoBusiness;
	
	@RequestMapping(value = "/pesquisa/produtos", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(Public.class)
	public List<Produto> pesquisaProdutos(Model model, @RequestParam("pesquisa") String pesquisa) {
		
		List<Produto> produtos = produtoBusiness.pesquisaProdutos(pesquisa, 10);
		return produtos;
		
	}

}
