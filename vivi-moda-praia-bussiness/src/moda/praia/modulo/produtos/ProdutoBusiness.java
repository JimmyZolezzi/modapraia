package moda.praia.modulo.produtos;

import java.util.List;

import org.springframework.stereotype.Component;

import moda.praia.modulo.produtos.bean.Categoria;
import moda.praia.modulo.produtos.bean.ImagemProduto;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.modulo.produtos.bean.Subcategoria;

public interface ProdutoBusiness {

	public boolean cadastrarProduto(Produto produto);
	public boolean cadastrarCategoria(Categoria categoria);		
	public boolean cadastrarSubCategoria(Subcategoria subcategoria);
	public boolean cadastrarImagemProduto(Produto produto, ImagemProduto imagemProduto);
	public boolean removerImagemProduto(Produto produto, ImagemProduto imagemProduto);
	
	//Pesquisa de Produtos
	public Produto pesquisarProduto(long id);
	public List<Produto> pesquisarProdutos(int campoOrdenacao, int posicaoInicial, int quantidadeRegistros);
	public List<Produto> pesquisarProdutosPalavraChave(String palavraChave, int quantidadeMaxRegistros);
	public List<Produto> pesquisaProdutos();
	public List<Produto> pesquisaProdutos(int idCategoria);
	public List<Produto> pesquisaProdutos(Subcategoria subcategoria);
	public List<Produto> pesquisaProdutos(String descricao);
	public boolean removerProduto(long id);
	public boolean alterarProduto(Produto produto);
	//Pesquisa de Categorias	
	public Categoria pesquisarCategoria(int id);
	public List<Categoria> pesquisarCategorias();
	public List<Categoria> pesquisarCategorias(int campoOrdenacao, int posicaoInicial, int quantidadeRegistros);
	public List<Categoria> pesquisarCategoriasPalavraChave(String palavraChave, int quantidadeMaxRegistros);
	//Pesquisa de Subcategorias
	public Subcategoria pesquisarSubcategoria(int id);
	public List<Subcategoria> pesquisarSubcategoria();
	public List<Subcategoria> pesquisarSubcategoria(Categoria categoria);
	public List<Subcategoria> pesquisarSubcategorias(int campoOrdenacao, int posicaoInicial, int quantidadeRegistros);
	public List<Subcategoria> pesquisarSubcategoriasPalavraChave(String palavraChave, int quantidadeMaxRegistros);
	//Consulta Imagem
	public ImagemProduto pesquisaImagemProdutoPorID(long id);
	
	
}
