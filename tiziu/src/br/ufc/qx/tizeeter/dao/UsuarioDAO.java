package br.ufc.qx.tizeeter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.internal.matchers.And;

import java.sql.Date;

import br.ufc.qx.tizeeter.model.ConnectionFactory;
import br.ufc.qx.tizeeter.model.Usuario;

public class UsuarioDAO {
	private Connection connection;
	public UsuarioDAO(ConnectionFactory connection) {
		try {
			this.connection = connection.getConnection();
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param usuario
	 * @return null em caso de erro.
	 * Em caso de sucesso deve retorna o usuario informado
	 * via parâmetro
	 */
	public Usuario adiciona(Usuario usuario) {		
		String sql = "INSERT INTO usuario(login, email, senha, nome, endereco, data_de_nascimento, sexo, novidades)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getNomeDeUsuario());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getSenha());
			statement.setString(4, usuario.getNome());
			statement.setString(5, usuario.getEndereco());
			statement.setDate(6, (Date) usuario.getDataDeNascimento()); // TODO: solve properly the Date type issue
			statement.setString(7, Character.toString(usuario.getSexo())); 
			statement.setBoolean(8, usuario.getNovidades());
			int success = statement.executeUpdate();
			statement.close();
			connection.close();
			return success > 0? usuario : null;
		}
		catch (SQLException sqle) {
			throw new RuntimeException();
		}
	}

	public List<Usuario> getTodos() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuario";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getLong("id"));
				usuario.setNomeDeUsuario(resultSet.getString("login"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setEndereco(resultSet.getString("endereco"));
				usuario.setSexo(resultSet.getString("sexo").charAt(0));
				usuario.setNovidades(resultSet.getBoolean("novidades"));
				usuarios.add(usuario);
			}
			statement.close();
			resultSet.close();
		}
		catch (SQLException sqle) {
			throw new RuntimeException();
		}
		return usuarios;
	}
	public boolean existe(Usuario usuario) throws SQLException {
		try {
			String sql = "SELECT FROM usuario WHERE login=? OR email=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getNomeDeUsuario());
			statement.setString(2, usuario.getEmail());
			ResultSet result = statement.executeQuery();
			boolean exists = result.first();
			statement.close();
			result.close();
			return exists;
		}
		catch (SQLException sqle) {
			throw new RuntimeException();
		}
	}

}
