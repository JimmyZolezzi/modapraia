package moda.praia.modulo.produtos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		em.merge(produto);
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
