package sql_jdbc_postgres.co.vilsonjesuino;

import java.util.List;

import org.junit.Test;

import sql_jdbc_postgres.co.vilsonjesuino.conexao.SingleConnection;
import sql_jdbc_postgres.co.vilsonjesuino.dao.UsuarioDao;
import sql_jdbc_postgres.co.vilsonjesuino.model.Usuario;

public class TesteConexao {

	@Test
	public void testeConexao() {
		SingleConnection.getConnection();
	}
	
	@Test
	public void testeSalvar() {
		
		Usuario usuario = new Usuario();
		
		usuario.setId(4L);
		usuario.setNome("maria");
		usuario.setEmail("maria@gmail.com");
		
		
		UsuarioDao dao = new UsuarioDao();
		dao.salvar(usuario);
	}
	
	@Test
	public void testeListarUsuario() throws Exception {
		UsuarioDao dao = new UsuarioDao();
		List<Usuario> listarUsuario = dao.listarUsuario();
		
		for(Usuario usuario : listarUsuario) {
			System.out.println(usuario);
			System.out.println("-----------------------------");
		}
	}
	
	@Test
	public void testeListarUnicoUsuario() throws Exception{
		
		UsuarioDao dao = new UsuarioDao();
		
		Usuario usuario = dao.listarUnicoUsuario(1L);
		
		System.out.println(usuario);
	}
	
	@Test
	public void testeAtualizar() {
		
		try {
			
			UsuarioDao dao = new UsuarioDao();
			
			Usuario usuario = dao.listarUnicoUsuario(4L);
			
			usuario.setNome("mudado com metodo atualizar");
			
			dao.atualizar(usuario);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
