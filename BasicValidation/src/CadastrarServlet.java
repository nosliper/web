import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastrarServlet {

	private void paginaSucesso(PrintWriter out, String nome, String email) {
		
		String head = "<html><head><title>Cadastro</title></head><body>";
		String body = "</body></html>";
		
		out.println(head);
		
		Connection con = new ConnectionFactory().getConnection();
		final String sql= "INSERT INTO contato (nome, email)"
				+ " VALUES (?, ?)";
		try {
			PreparedStatement stat = con.prepareStatement(sql);
			stat.setString(1, nome);
			stat.setString(2, email);
			
			if(stat.executeUpdate() > 0){
				out.print("<h2> Contato inserido com sucesso </h2>");
			} else {
				out.print("<h2> Erro durante inserção. Tente novamente </h2>");
			}
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.print("<h3> Contatos cadastrados:</h3>");
		out.print("<table>");
		out.print("<tr>"
					+ "<th>Nome</th>"
					+ "<th>Email</th>"
					+ "<th>Editar</th>"
					+ "<th>Deletar</th>"
				+ "</tr>");
		
		final String sqlquery = "SELECT * from contato;";
		
		try {
			PreparedStatement stat = con.prepareStatement(sqlquery);
			if(stat.execute()){
				ResultSet result = stat.getResultSet();
				while(result.next()){
					out.print("<tr><td>" + result.getString("nome") + "</td>");
					out.print("<td>" + result.getString("email") + "</td>");
					out.print("<td><a href=\"editar?id=" + result.getInt("id") + "\">Editar</a></td>");
					out.print("<td><a href=\"deletar?id=" + result.getInt("id") + "\">Deletar</a></td>");							
					out.print("</tr>");
				}
			}
			out.print("</table>");
			stat.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.print("</table>");
		
		
		out.println(body);
	}
}