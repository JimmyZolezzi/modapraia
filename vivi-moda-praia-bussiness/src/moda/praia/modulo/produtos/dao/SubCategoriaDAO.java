package moda.praia.modulo.produtos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import moda.praia.modulo.produtos.bean.Categoria;
import moda.praia.modulo.produtos.bean.Subcategoria;

import org.springframework.stereotype.Repository;


@Repository
public class SubCategoriaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void adicionaSubCategoria(Subcategoria subcategoria){
		em.persist(subcategoria);
	}
	
	public void alteraSubCategoria(Subcategoria subcategoria){
		em.merge(subcategoria);
	}
	
	public void removeSubCategoria(Subcategoria subcategoria){
		Subcategoria subcategoriaBuscada = buscaPorId(subcategoria.getId());
		em.remove(subcategoriaBuscada);
	}
	
	public Subcategoria buscaPorId(int id) {
	      return em.find(Subcategoria.class, id);
	}
	
	public List<Subcategoria> lista(){
		return em.createQuery("select c from Subcategoria c").getResultList();
	}
	
	public List<Subcategoria> listaEager(){
		return em.createQuery("select c from Subcategoria c JOIN FETCH c.categoria ").getResultList();
	}

	public List<Subcategoria> lista(Categoria categoria){
		Query query  = em.createQuery("select s from Subcategoria s JOIN FETCH s.categoria where s.categoria =:categoria ");
		query.setParameter("categoria", categoria);
		return query.getResultList();
	
	}
}
