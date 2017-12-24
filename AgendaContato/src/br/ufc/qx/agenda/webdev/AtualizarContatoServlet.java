package br.ufc.qx.agenda.webdev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="atualizarContato", urlPatterns="/editar")
public class AtualizarContatoServlet extends HttpServlet {
	
	private static final long serialVersionUID = -1913446626770214644L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		ContatoDAO dao = new ContatoDAO();
		
		Contato contato = dao.getById(id);
		
		PrintWriter out = resp.getWriter();
		
		
		out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\">"
				+ "<title>Cadastro contato</title></head>"
				+ "<body>");
		out.println("<form action=\"editar\" method=\"POST\">");
		out.println("<input type=\"hidden\" name=\"id\" value=\""+ contato.getId() + "\">");
		out.println("<label for=\"nome\">Nome:</label>");
		out.println("<input type=\"text\" name=\"nome\" value=\"" + contato.getNome() + "\"><br/>");
		out.println("<label for=\"email\">Email:</label>");
		out.println("<input type=\"text\" name=\"email\" value=\"" + contato.getEmail() + "\"><br/>");
		out.println("<input type=\"submit\" value=\"Enviar\"/>");
		out.println("</form>");
		out.println("<a href=\"listar\">Ver todos os contatos</a>");
		out.println("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		
		Contato c = new Contato(id, nome, email);
		
		ContatoDAO dao = new ContatoDAO();
		
		String head = "<html><head><title>Atualizar contato</title></head><body>";
		String body = "</body></html>";

		PrintWriter out = resp.getWriter();
		out .println(head);

		if(dao.update(c)){
			out.println("<h2> Contato atualizado com sucesso.</h2>");
		} else {
			out.println("<h2> Não foi possivel atualizar o contato desejado</h2>");
		};
		out.println("<a href=\"listar\">Listar contatos</a>");
		out.println(body);

	}
}
