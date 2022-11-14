package sql_jdbc_postgres.co.vilsonjesuino.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql_jdbc_postgres.co.vilsonjesuino.conexao.SingleConnection;
import sql_jdbc_postgres.co.vilsonjesuino.model.BeanUsuarioTelefone;
import sql_jdbc_postgres.co.vilsonjesuino.model.Telefone;
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
	 * Method salvar dados do telefone do usuario
	 */

	public void salvarTelefone(Telefone telefone) {

		try {

			String sql = "insert into tbl_telefone (numero, tipo, usuariopessoa) values (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
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

	public List<BeanUsuarioTelefone> listaUserFone (Long idUser){
		
		List<BeanUsuarioTelefone> beanUsuarioTelefones = new ArrayList<BeanUsuarioTelefone>();
		
		String sql = "select nome, numero, email from tbl_telefone as fone";
		sql += "inner join tbl_usuario as usua";
		sql += "on fone.usuariopessoa = usua.id";
		sql += "where usua.id = " + idUser;
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				
				BeanUsuarioTelefone beanUsuarioTelefone = new BeanUsuarioTelefone();
				
				beanUsuarioTelefone.setEmail(resultado.getString("email"));
				beanUsuarioTelefone.setNome(resultado.getString("nome"));
				beanUsuarioTelefone.setNumero(resultado.getString("numero"));
				
				beanUsuarioTelefones.add(beanUsuarioTelefone);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro aqui no banco de dados");
		}
		
		return beanUsuarioTelefones;
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

	/**
	 * Method delete dados no banco de dados
	 * 
	 * @param id
	 */

	public void deletar(Long id) {

		try {

			String sql = "delete from tbl_usuario where id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
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
