package sql_jdbc_postgres.co.vilsonjesuino.dao;

import java.sql.Connection;

import sql_jdbc_postgres.co.vilsonjesuino.conexao.SingleConnection;

public class UsuarioDao {
	
	private Connection connection;
	
	public UsuarioDao() {
		connection = SingleConnection.getConnection();
	}

}
