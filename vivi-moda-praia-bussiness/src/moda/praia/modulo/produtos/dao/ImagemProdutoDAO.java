package moda.praia.modulo.produtos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import moda.praia.modulo.produtos.bean.Categoria;
import moda.praia.modulo.produtos.bean.ImagemProduto;

import org.springframework.stereotype.Repository;

@Repository
public class ImagemProdutoDAO {

	@PersistenceContext
	private EntityManager em;
	
	
	public ImagemProduto buscaPorId(long id) {
		return em.find(ImagemProduto.class, id);
	}
	
	public void adicionaImagem(ImagemProduto imagemProduto){
		em.persist(imagemProduto);
	}
	
	public void alteraCategoria(ImagemProduto imagemProduto){
		em.merge(imagemProduto);
	}
	
	public void removeCategoria(ImagemProduto imagemProduto){
		ImagemProduto imagemProduto2 = buscaPorId(imagemProduto.getId());
		em.remove(imagemProduto2);
	}
	
}
