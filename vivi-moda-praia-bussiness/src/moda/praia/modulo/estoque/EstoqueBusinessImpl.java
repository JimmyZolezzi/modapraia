package moda.praia.modulo.estoque;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.modulo.produtos.repositorios.ProdutoRepository;

@Service
public class EstoqueBusinessImpl implements EstoqueBusiness {
	
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@Override
	public boolean entrarProdutoEstoque(Produto produto, Estoque estoque) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public Page<Produto> listaProdutoEstoque(Integer pageNumber) {
	        PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "descricao");
	        return produtoRepository.findAll(request);
	}


	@Override
	public Produto visualizarProdutoEstoque(long idProduto) {
		
		return produtoRepository.findById(idProduto);
		
	}
	

}
