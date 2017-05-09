package moda.praia.modulo.pedido.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.pedido.bean.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long>{

	public Page<Pedido> findAll(Pageable pageable);
	public Pedido findById(long id);
	public Page<Pedido> findByCliente(Pageable pageable,Cliente cliente);
	
	
}
