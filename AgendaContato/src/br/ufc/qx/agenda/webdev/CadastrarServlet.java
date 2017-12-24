package br.ufc.qx.agenda.webdev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "cadastrar", urlPatterns = "/cadastrar")
public class CadastrarServlet extends HttpServlet {

	private static final long serialVersionUID = 8344784317019129668L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nome = req.getParameter("nome");
		String email = req.getParameter("email");

		PrintWriter out = resp.getWriter();

		if ((nome == null || nome.isEmpty()) || (email == null || email.isEmpty())) {
			mostrarErro(out);
		} else {
			paginaSucesso(out, nome, email);
		}
	}

	private void paginaSucesso(PrintWriter out, String nome, String email) {

		String head = "<html><head><title>Cadastro</title></head><body>";
		String body = "</body></html>";

		out.println(head);

		ContatoDAO dao = new ContatoDAO();
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);

		if (dao.insert(contato)) {
			out.print("<h2> Contato inserido com sucesso </h2>");
		} else {
			out.print("<h2> Erro durante inserção. Tente novamente </h2>");
		}

		out.println("<a href=\"listar\">Listar contatos</a>");
		out.println(body);
	}

	private void mostrarErro(PrintWriter out) {

		String head = "<html><head><title>Erro</title></head><body>";
		String body = "</body></html>";

		out.println(head);

		out.print("<h2> Contato não inserido. " + "Verifique o nome e o email informado! </h2>");

		out.println(body);

	}
}
