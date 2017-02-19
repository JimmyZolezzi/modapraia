package moda.praia.modulo.administradores.bean;

import java.util.List;

public interface AdministradorBusiness {

	public boolean logarSistema(String usuario, String senha);
	public boolean verificarAdministradoroLogado(Adminitrador administrador);
	public boolean cadastrarAdminitrador(Adminitrador adminitrador);
	public List<Adminitrador> obterAdminitradores(int posInicial, int quantidadeRegistros);
	public Adminitrador obterAdminitrador(long id);
}
