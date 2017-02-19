package test.produto.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import moda.praia.config.JPAConfig;
import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.Categoria;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfig.class})
public class TestAdicionarCategoriaTest {
	
	
	@Autowired
	private ProdutoBusiness produtoBusiness;

	@Test
	public void testCadastroCategoria(){
		Categoria categoria = new Categoria();
		categoria.setDescricao("categoria teste");
		produtoBusiness.cadastrarCategoria(categoria);
	}


}