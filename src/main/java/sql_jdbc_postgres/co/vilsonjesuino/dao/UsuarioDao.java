package sql_jdbc_postgres.co.vilsonjesuino.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql_jdbc_postgres.co.vilsonjesuino.conexao.SingleConnection;
import sql_jdbc_postgres.co.vilsonjesuino.model.Usuario;

public class UsuarioDao {

	private Connection connection;

	public UsuarioDao() {
		connection = SingleConnection.getConnection();
	}

	/**
	 * Method salvar
	 * 
	 * @param usuario
	 */

	public void salvar(Usuario usuario) {

		String sql = "insert into tbl_usuario (nome, email) values (?, ?)";

		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getNome());
			insert.setString(2, usuario.getEmail());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method List Usuario
	 * 
	 * @return list
	 */

	public List<Usuario> listarUsuario() throws Exception {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		String sql = "select * from tbl_usuario";

		PreparedStatement selectList = connection.prepareStatement(sql);
		ResultSet resultado = selectList.executeQuery();

		while (resultado.next()) {
			Usuario usuario = new Usuario();

			usuario.setId(resultado.getLong("id"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setEmail(resultado.getString("email"));

			usuarios.add(usuario);
		}

		return usuarios;
	}

	/**
	 * Lista somente um objeto
	 * 
	 * @param id
	 */

	public Usuario listarUnicoUsuario(Long id) throws Exception {

		Usuario retorno = new Usuario();

		String sql = "select * from tbl_usuario where id = " + id;

		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultado = select.executeQuery();

		while (resultado.next()) {

			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
		}

		return retorno;
	}

	/**
	 * Method atualiza no banco de dados
	 * 
	 * @param usuario
	 * @throws SQLException
	 */

	public void atualizar(Usuario usuario) throws SQLException {

		try {

			String sql = "update tbl_usuario set nome = ? where id = " + usuario.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getNome());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
