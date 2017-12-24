package br.ufc.qx.agenda.webdev;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "deletarContato", urlPatterns = "/deletar")
public class RemoverContato extends HttpServlet {

	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		Contato c = new Contato();
		c.setId(id);
		
		ContatoDAO dao = new ContatoDAO();
		PrintWriter out = resp.getWriter();
		
		String head = "<html><head><title>Remover contato</title></head><body>";
		String body = "</body></html>";

		out.println(head);

		if(dao.remove(c)){
			out.print("<h2> Contato removido com sucesso.</h2>");
		} else {
			out.print("<h2> Não foi possivel remover o contato desejado</h2>");
		};
		out.println("<a href=\"listar\">Listar contatos</a>");
		out.println(body);
		
		
	};
}
