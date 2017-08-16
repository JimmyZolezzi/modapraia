package moda.praia.modulo.produtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import moda.praia.modulo.produtos.bean.Categoria;
import moda.praia.modulo.produtos.bean.ImagemProduto;
import moda.praia.modulo.produtos.bean.ItemProduto;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.modulo.produtos.bean.Subcategoria;
import moda.praia.modulo.produtos.dao.CategoriaDAO;
import moda.praia.modulo.produtos.dao.ImagemProdutoDAO;
import moda.praia.modulo.produtos.dao.ProdutoDAO;
import moda.praia.modulo.produtos.dao.SubCategoriaDAO;
import moda.praia.modulo.produtos.repositorios.ItemProdutoRepository;
import moda.praia.modulo.produtos.repositorios.ProdutoRepository;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class ProdutoBusinessImpl implements ProdutoBusiness{

	private static final int PAGE_SIZE = 10;
	
	private final Logger log = Logger.getLogger(ProdutoBusinessImpl.class);
	
	private final CategoriaDAO categoriaDAO;
	private final SubCategoriaDAO subCategoriaDAO;
	private final ImagemProdutoDAO imagemProdutoDAO;
	private final ProdutoDAO produtoDAO;
	private final ProdutoRepository produtoRepository;
	private final ItemProdutoRepository itemProdutoRepository;
	@Autowired
	public ProdutoBusinessImpl(CategoriaDAO categoriaDAO, SubCategoriaDAO subCategoriaDAO, 
			ProdutoDAO produtoDAO,ImagemProdutoDAO imagemProdutoDAO, ItemProdutoRepository itemProdutoRepository, 
			ProdutoRepository produtoRepository){
		this.categoriaDAO = categoriaDAO;
		this.subCategoriaDAO = subCategoriaDAO;
		this.imagemProdutoDAO = imagemProdutoDAO;
		this.produtoDAO = produtoDAO;
		this.itemProdutoRepository = itemProdutoRepository;
		this.produtoRepository = produtoRepository;
	}
	
	
	@Override
	public boolean cadastrarProduto(Produto produto) {
		
		try{
			if(produto != null){
				BigDecimal descontoPercentual = produto.getDescontoPercentual();
				BigDecimal valor = produto.getValor();
				if(produto.getDescontoPercentual() != null && produto.getValor() != null){
					BigDecimal valorDesconto =calcularValorProdutoComDesconto(valor, descontoPercentual);
					produto.setDescontoValor(valorDesconto);
				}
				produtoDAO.adicionaProduto(produto);
				
				return true;
				
			}
			
			return false;
			
		}catch(Exception e){
			log.error("Erro ao cadastrar produto: " + e);
			return false;
		}
	}

	@Override
	public boolean cadastrarCategoria(Categoria categoria) {
		
		try{
			categoriaDAO.adicionaCategoria(categoria);
			return true;
		}catch(Exception e){
			log.error("Erro ao adicionar categoria: " + e);
			return false;
		}
		
	}

	@Override
	@Transactional
	public boolean cadastrarSubCategoria(Subcategoria subcategoria) {
		// TODO Auto-generated method stub
		try{
			subCategoriaDAO.adicionaSubCategoria(subcategoria);
			return true;
		}catch(Exception e){
			log.error("Erro ao adicionar subcategoria: " + e);
			return false;
		}
	}

	@Override
	public Produto pesquisarProduto(long id) {
		
		try{
			return produtoDAO.buscaPorIdEager(id);
			
		}catch(Exception e){
			log.error("Erro ao pesquisar produto: " + e);
		}
		
		return null;
	}

	@Override
	public List<Produto> pesquisarProdutos(int campoOrdenacao,
			int posicaoInicial, int quantidadeRegistros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produto> pesquisarProdutosPalavraChave(String palavraChave,
			int quantidadeMaxRegistros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria pesquisarCategoria(int id) {

		try{
			return categoriaDAO.buscaPorId(id);
		
		}catch(Exception e){
			log.error("Erro ao pesquisar categoria: " + e);
		}
		return null;
	}

	@Override
	public List<Categoria> pesquisarCategorias(int campoOrdenacao,
			int posicaoInicial, int quantidadeRegistros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> pesquisarCategoriasPalavraChave(String palavraChave,
			int quantidadeMaxRegistros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subcategoria pesquisarSubcategoria(int id) {
		
		try{
			Subcategoria subcategorias = subCategoriaDAO.buscaPorId(id);
			
			return subcategorias;
			
		}catch(Exception e){
			log.error("Erro ao pesquisar subcategoria: " + e);
			return null;
		}
	}

	@Override
	public List<Subcategoria> pesquisarSubcategorias(int campoOrdenacao,
			int posicaoInicial, int quantidadeRegistros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subcategoria> pesquisarSubcategoriasPalavraChave(
			String palavraChave, int quantidadeMaxRegistros) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Categoria> pesquisarCategorias() {

		try{
			List<Categoria> categorias = categoriaDAO.lista();
			
			return categorias;
			
		}catch(Exception e){
			log.error("Erro ao pesquisar categoria: " + e);
		}
		return null;
	}


	@Override
	public List<Subcategoria> pesquisarSubcategoria() {
		try{
			return subCategoriaDAO.listaEager();
			
		}catch(Exception e){
			log.error("Erro ao pesquisar subcategoria: " + e);
		}
		
		return null;
	}


	@Override
	public List<Subcategoria> pesquisarSubcategoria(Categoria categoria) {

		try{
			return subCategoriaDAO.lista(categoria);
			
		}catch(Exception e){
			log.error("Erro ao pesquisar subcategoria por categoria: " + e);
		}
		
		return null;
	}


	@Override
	public ImagemProduto pesquisaImagemProdutoPorID(long id) {

		try{
			
			return imagemProdutoDAO.buscaPorId(id);
			
		}catch(Exception e){
			log.error("Erro ao pesquisar imagem de produto " + e);
		}
		return null;
	}


	@Override
	public List<Produto> pesquisaProdutos() {

		try{
			
			return produtoDAO.lista();
			
		}catch(Exception e){
			log.error("Erro ao pesquisar produtos " + e);
		}
		
		return null;
	}


	@Override
	public boolean removerProduto(long id) {
		
		try{
			
			produtoDAO.removeProduto(id);
			return true;
		
		}catch(Exception e){
			log.error("Erro ao tentar remover produto" + e);
			return false;

		}
	}


	@Override
	public boolean alterarProduto(Produto produto) {

		try{
			Produto produtoManaged = produtoDAO.buscaPorIdEager(produto.getId());
			//Calcular valor de desconto 
			if(produto != null){
				
				BigDecimal descontoPercentual = produto.getDescontoPercentual();
				BigDecimal valor = produto.getValor();
				if(produto.getDescontoPercentual() != null && produto.getValor() != null){
					BigDecimal valorDesconto =calcularValorProdutoComDesconto(valor, descontoPercentual);
					produto.setDescontoValor(valorDesconto);
				}
				
				myCopyProperties(produto, produtoManaged);
				
				produtoDAO.alteraProduto(produtoManaged);
				
			}
			return true;
		
		}catch(Exception e){
			log.error("Erro ao tentar alterar produto" + e);
			return false;

		}
	}
	
	@Override
	public List<Produto> pesquisaProdutos(int idCategoria) {
		
		try{
			List<Produto> produtos = produtoDAO.listaPorCategoria(idCategoria);
			return produtos;
			
		}catch(Exception e){
			log.error("erro ao pesquisar produto por categoria" + e.getStackTrace());

		}
		return null;
	}
	
	@Override
	public List<Produto> pesquisaProdutos(Subcategoria subcategoria) {
		
		try{
			List<Produto> produtos = produtoDAO.listaPorSubCategoria(subcategoria);
			return produtos;
			
		}catch(Exception e){
			log.error("erro ao pesquisar produto por subcategoria" + e.getStackTrace());

		}
		return null;
	}



	@Override
	public List<Produto> pesquisaProdutos(String descricao, int quantidade) {

		try{
			return produtoDAO.pesquisaPorDescricao(descricao, quantidade);
		
		}catch(Exception e){
			log.error("erro ao pesquisar produto por descricao" + e.getStackTrace());

		}
		return null;
	}
	
	
	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}

	// then use Spring BeanUtils to copy and ignore null
	private static void myCopyProperties(Object src, Object target) {
	    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}


	@Override
	public boolean cadastrarImagemProduto(Produto produto, ImagemProduto imagemProduto) {
		try{
			if(produto != null && imagemProduto !=null){
				produto = produtoDAO.buscaPorIdEager(produto.getId());

				List<ImagemProduto> imagensProduto = produto.getImagensProduto();

				if(imagensProduto == null){
					imagensProduto = new ArrayList<ImagemProduto>();
					produto.setImagensProduto(imagensProduto);
				}
				
				imagensProduto.add(imagemProduto);
				produtoDAO.alteraProduto(produto);
				
				return true;
			}
			
		}catch(Exception e){
			log.error("erro ao cadastrar imagem do produto" + e.getStackTrace());
			return false;
			
		}
		return false;
		
	}


	@Override
	public boolean removerImagemProduto(Produto produto,
			ImagemProduto imagemProduto) {

		try{
			if(produto != null && imagemProduto !=null){
				List<ImagemProduto> imagensProduto = produto.getImagensProduto();

				Produto produtoManaged = produtoDAO.buscaPorIdEagerImagensProduto(produto.getId());
				produtoDAO.refresh(produtoManaged);
				if(imagensProduto !=null){
					produtoManaged.getImagensProduto().remove(imagemProduto);
				}
				produtoDAO.alteraProduto(produtoManaged);
				
				return true;
			}
			
		}catch(Exception e){
			log.error("erro ao cadastrar imagem do produto" + e.getStackTrace());
			return false;
			
		}
		return false;
	}


	@Override
	public ItemProduto pesquisaItemProduto(long id) {
		
		try{
			return itemProdutoRepository.findById(id);
			
		}catch(Exception e){
			log.error("erro ao pesquisar item produto" + e.getStackTrace());
		}
		return null;
	}


	@Override
	public Produto pesquisaProdutoCarrinho(long id) {
		
		try{
			return produtoDAO.buscaProdutoCarrinhoPorId(id);
			
		}catch(Exception e){
			log.error("Erro ao pesquisar produto: " + e);
		}
		
		return null;
	}


	@Override
	public BigDecimal calcularValorProdutoComDesconto(BigDecimal valorProduto, BigDecimal percenutalDesconto) {
		
		return valorProduto.subtract(valorProduto.multiply(percenutalDesconto.divide(new BigDecimal(100))));
	}


	@Override
	public Page<Produto> pesquisaProdutos(Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "descricao");
		return produtoRepository.findAll(request);
	}


	@Override
	public Page<Produto> pesquisaProdutosDestaque(Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "descricao");
		// TODO Auto-generated method stub
		return produtoRepository.findByDestaque(true, request);
	}




	
}
