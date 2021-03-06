package moda.praia.modulo.clientes.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moda.praia.modulo.clientes.bean.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
	
	public Page<Cliente> findAll(Pageable pageable);
	public Cliente findById(long id);
	@Query("select c from Cliente c left JOIN FETCH c.enderecoEntrega where c.email = ?1")
	public Cliente findByEmail(String email);
}
