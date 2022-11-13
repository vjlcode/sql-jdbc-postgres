package sql_jdbc_postgres.co.vilsonjesuino;

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
}
