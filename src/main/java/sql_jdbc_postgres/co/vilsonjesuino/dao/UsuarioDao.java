package sql_jdbc_postgres.co.vilsonjesuino.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import sql_jdbc_postgres.co.vilsonjesuino.conexao.SingleConnection;
import sql_jdbc_postgres.co.vilsonjesuino.model.Usuario;

public class UsuarioDao {

	private Connection connection;

	public UsuarioDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Usuario usuario) {

		String sql = "insert into tbl_usuario (id, nome, email) values (?, ?, ?)";

		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, usuario.getId());
			insert.setString(2, usuario.getNome());
			insert.setString(3, usuario.getEmail());
			insert.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
