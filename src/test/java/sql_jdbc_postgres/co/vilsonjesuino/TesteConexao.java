package sql_jdbc_postgres.co.vilsonjesuino;

import org.junit.Test;

import sql_jdbc_postgres.co.vilsonjesuino.conexao.SingleConnection;

public class TesteConexao {

	@Test
	public void testeConexao() {
		SingleConnection.getConnection();
	}
}
