package br.ufc.qx.agenda.webdev;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ListarContato", urlPatterns="/listar")
public class ListarContatoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		String head = "<html><head><title>Cadastro</title></head><body>";
		String body = "</body></html>";
		
		out.println(head);

		out.print("<h3> Contatos cadastrados:</h3>");
		out.print("<table>");
		out.print("<tr>" + "<th>Nome</th>" + "<th>Email</th>" + "<th>Editar</th>" + "<th>Deletar</th>" + "</tr>");


		ContatoDAO dao = new ContatoDAO();
		List<Contato> contatos = dao.getAll();
		
		for (Contato contato : contatos) {
			out.print("<tr><td>" + contato.getNome() + "</td>");
			out.print("<td>" + contato.getEmail() + "</td>");
			out.print("<td><a href=\"editar?id=" + contato.getId() + "\">Editar</a></td>");
			out.print("<td><a href=\"deletar?id=" + contato.getId() + "\">Deletar</a></td>");
			out.print("</tr>");
		}
		out.print("</table>");
		out.print(body);
	}
}
