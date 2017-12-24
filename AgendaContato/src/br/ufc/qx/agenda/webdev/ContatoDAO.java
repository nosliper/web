package br.ufc.qx.agenda.webdev;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO extends GenericDAO<Contato> {

	@Override
	public List<Contato> getAll() {

		final String sql = "SELECT * from contato;";
		List<Contato> contatos = new ArrayList<Contato>();

		try {
			PreparedStatement stat = con.prepareStatement(sql);
			if (stat.execute()) {
				ResultSet result = stat.getResultSet();
				while (result.next()) {
					Contato c = new Contato(result.getInt("id"),
							result.getString("nome"),
							result.getString("email")
					);
					contatos.add(c);
				}
			}
			stat.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contatos;
	}

	@Override
	public boolean insert(Contato contato) {
		
		final String sql= "INSERT INTO contato (nome, email)"
				+ " VALUES (?, ?)";
		try {
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, contato.getNome());
			stat.setString(2, contato.getEmail());
			
			boolean result = stat.executeUpdate() > 0;
			
			stat.close();
			con.close();
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean remove(Contato entity) {
		
		final String sql = "DELETE FROM contato WHERE id = ?";
		
		try {
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setInt(1, entity.getId());
			return stat.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Contato getById(int id) {
		final String sql = "SELECT * FROM contato WHERE id = ?";

		try {
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setInt(1, id);
			
			if (stat.execute()) {
				ResultSet result = stat.getResultSet();
				result.first();
				
					Contato c = new Contato(result.getInt("id"),
							result.getString("nome"),
							result.getString("email")
					);
				return c;
			}
			stat.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Contato contato) {
		
		String sql = "UPDATE contato SET nome = ?, email = ? WHERE id = ?";
		
		PreparedStatement stat;
		try {
			stat = con.prepareStatement(sql);
			stat.setInt(3, contato.getId());
			stat.setString(1, contato.getNome());
			stat.setString(2, contato.getEmail());
			
			boolean resultado = stat.executeUpdate() > 0;
			stat.close();
			con.close();
			return resultado;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}














