package moda.praia.modulo.produtos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import moda.praia.modulo.produtos.bean.ItemProdutoEstoque;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.modulo.produtos.bean.Subcategoria;

import org.springframework.stereotype.Repository;

@Repository
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void refresh(Produto produto){
		em.refresh(produto);
	}
	
	public void adicionaProduto(Produto produto){
		em.persist(produto);
	}
	
	public void alteraProduto(Produto produto){
		
		if(produto != null && produto.getId() != 0){
			
			StringBuilder sb = new StringBuilder();
			sb.append("update Produto p set p.descricao = :descricao, p.informacoes =:informacoes, p.valor =:valor,");
			sb.append("p.descontoValor =:descontoValor, p.descontoPercentual =:descontoPercentual, p.categoria =:categoria, ");
			sb.append("p.subcategoria =:subcategoria, p.imagemProduto1 =:imagemProduto1, p.imagemProduto2 =:imagemProduto2, ");
			sb.append("p.comprimento =:comprimento, p.altura =:altura, p.largura =:largura, p.corPredominante =:corPredominante, p.peso =:peso, ");
			sb.append("p.aplicarDesconto =:aplicarDesconto ");
			sb.append("where p.id =:idProduto");
			
			String query = sb.toString();
			
			Query queryUpdate = em.createQuery(query);
			
			queryUpdate.setParameter("descricao", produto.getDescricao());
			queryUpdate.setParameter("informacoes", produto.getInformacoes());
			queryUpdate.setParameter("valor", produto.getValor());
			queryUpdate.setParameter("descontoValor", produto.getDescontoValor());
			queryUpdate.setParameter("descontoPercentual", produto.getDescontoPercentual());
			queryUpdate.setParameter("categoria", produto.getCategoria());
			queryUpdate.setParameter("subcategoria", produto.getSubcategoria());
			queryUpdate.setParameter("descontoValor", produto.getDescontoValor());
			queryUpdate.setParameter("imagemProduto1", produto.getImagemProduto1());
			queryUpdate.setParameter("imagemProduto2", produto.getImagemProduto2());
			queryUpdate.setParameter("comprimento", produto.getComprimento());
			queryUpdate.setParameter("altura", produto.getAltura());
			queryUpdate.setParameter("largura", produto.getLargura());
			queryUpdate.setParameter("corPredominante", produto.getCorPredominante());
			queryUpdate.setParameter("peso", produto.getPeso());
			queryUpdate.setParameter("aplicarDesconto", produto.isAplicarDesconto());
			
			//Id Produto
			queryUpdate.setParameter("idProduto", produto.getId());
			queryUpdate.executeUpdate();
		}
		
	}
	
	public void removeProduto(Produto produto){
		Produto produtoBuscado = buscaPorId(produto.getId());
		em.remove(produtoBuscado);
	}
	public void removeProduto(long idProduto){
		Produto produtoBuscado = buscaPorId(idProduto);
		em.remove(produtoBuscado);
	}
	
	public Produto buscaPorId(long id) {
	      return em.find(Produto.class, id);
	}
	
	public Produto buscaPorIdEager(long id) {
		Query query = em.createQuery("select p from Produto p left JOIN FETCH p.categoria left JOIN FETCH p.subcategoria left JOIN FETCH p.imagemProduto1 left JOIN FETCH p.imagemProduto2 where p.id =:idProduto");
		query.setParameter("idProduto", id);
	    
		return (Produto) query.getSingleResult();
	}
	
	public Produto buscaProdutoCarrinhoPorId(long id) {
		Query query = em.createQuery("select p from Produto p left JOIN FETCH p.categoria left JOIN FETCH p.subcategoria left JOIN FETCH p.imagemProduto1 left JOIN FETCH p.imagemProduto2 where p.id =:idProduto");
		query.setParameter("idProduto", id);
	    Produto produto  = (Produto) query.getSingleResult();
	  
		return produto;
	}
	
	public List<ItemProdutoEstoque> buscaItemProdutoEstoque(long idItemProduto){
		
		Query queryItemProdutoEstoque = em.createQuery("select ipe from ItemProdutoEstoque ipe  where ipe.id =:idItemProduto and ipe.quantidade > 0");
		queryItemProdutoEstoque.setParameter("idItemProduto", idItemProduto);
		
		return queryItemProdutoEstoque.getResultList();
		
	}
	
	public Produto buscaPorIdEagerImagensProduto(long id) {
		Query query = em.createQuery("select p from Produto p left JOIN FETCH p.categoria left JOIN FETCH p.subcategoria left JOIN FETCH p.imagemProduto1 left JOIN FETCH p.imagemProduto2 left JOIN FETCH p.imagensProduto where p.id =:idProduto");
		query.setParameter("idProduto", id);
	    
		return (Produto) query.getSingleResult();
	}
	
	public List<Produto> lista(){
		return em.createQuery("select p from Produto p left JOIN FETCH p.categoria left JOIN FETCH p.subcategoria left JOIN FETCH p.imagemProduto1 left JOIN FETCH p.imagemProduto2 ").getResultList();
	}
	
	public List<Produto> listaPorCategoria(int idCategoria){
		
		Query query = em.createQuery("select p from Produto p left JOIN FETCH p.categoria left JOIN FETCH p.subcategoria left JOIN FETCH p.imagemProduto1 left JOIN FETCH p.imagemProduto2 where p.categoria.id =:idCategoria");
		query.setParameter("idCategoria", idCategoria);
		return query.getResultList();
	}
	
	public List<Produto> pesquisaPorDescricao(String pesquisa, int quantidade){
		
		Query query = em.createQuery("select p from Produto p left JOIN FETCH p.categoria left JOIN FETCH p.subcategoria left JOIN FETCH p.imagemProduto1 left JOIN FETCH p.imagemProduto2 where p.descricao like :descricao or p.categoria.descricao like :categoria");
		query.setParameter("descricao", "%"+ pesquisa +"%");
		query.setParameter("categoria", "%"+ pesquisa +"%");
		query.setMaxResults(quantidade);

		return query.getResultList();
	}
	
	public List<Produto> listaPorSubCategoria(Subcategoria subcategoria){
		
		Query query = em.createQuery("select p from Produto p left JOIN FETCH p.categoria left JOIN FETCH p.subcategoria left JOIN FETCH p.imagemProduto1 left JOIN FETCH p.imagemProduto2 where p.subcategoria =:subcategoria");
		query.setParameter("subcategoria", subcategoria);
		
		return query.getResultList();
	}
}
