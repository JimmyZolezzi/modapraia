package moda.praia.modulo.produtos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import moda.praia.modulo.produtos.bean.Categoria;

@Repository
public class CategoriaDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void adicionaCategoria(Categoria categoria){
		em.persist(categoria);
	}
	
	public void alteraCategoria(Categoria categoria){
		em.merge(categoria);
	}
	
	public void removeCategoria(Categoria categoria){
		Categoria categoriaBuscada = buscaPorId(categoria.getId());
		em.remove(categoriaBuscada);
	}
	
	public Categoria buscaPorId(int id) {
	      return em.find(Categoria.class, id);
	}
	
	public List<Categoria> lista(){
		return em.createQuery("select c from Categoria c LEFT join FETCH c.subcategorias").getResultList();
	}
}
