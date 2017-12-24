package br.ufc.qx.tizeeter.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import br.ufc.qx.tizeeter.dao.UsuarioDAO;
import br.ufc.qx.tizeeter.model.ConnectionFactory;
import br.ufc.qx.tizeeter.model.Usuario;

@WebServlet(name = "CadastrarUsuarioController", urlPatterns = "/usuario/novo")
public class CadastrarUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO dao;
	private void showSucesso(PrintWriter output, String email) {
		String style = "<link rel=\"stylesheet\" type=\"\text/css\" href=\"css/cadastrar.css\">";
		String head = "<html><head><title>Sucesso</title>" + style + "</head><body>";
		String body = "</body></html>";
		String msg = "<div class=\"sucesso\">"
					+ "<p>Usu&aacute;rio cadastrado com sucesso</p>"
					+ "<p>Verifique seu email: " + email + " </p>"
					+ "<p><a src=\"/login.html\">Entrar</a></p>"
					+ "</div>";
		output.println(head);
		output.println(body);
		output.println(msg);
	}
	private void showFalha(PrintWriter output) {
		String style = "<link rel=\"stylesheet\" type=\"\text/css\" href=\"css/cadastrar.css\">";
		String head = "<html><head><title>Falha</title>" + style + "</head><body>";
		String body = "</body></html>";
		String msg = "<div class=\"sucesso\">"
				+ "<p>Erro</p>"
				+ "<p>Usuario com login ou email ja existentes</p>"
				+ "<p><a src=\"/\">Voltar</a></p>"
				+ "</div>";
		output.println(head);
		output.println(body);
		output.println(msg);
	}
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		try {
			Usuario usuario = new Usuario();
			UsuarioDAO dao = new UsuarioDAO(new ConnectionFactory());
			usuario.setNomeDeUsuario(req.getParameter("username"));
			usuario.setEmail(req.getParameter("email"));
			usuario.setSenha(req.getParameter("password"));
			usuario.setNome(req.getParameter("name"));
			usuario.setEndereco(req.getParameter("address"));
			SimpleDateFormat dateFmt = new SimpleDateFormat("dd/mm/yyyy");
			usuario.setDataDeNascimento(dateFmt.parse(req.getParameter("day") + "/" + req.getParameter("month") + "/" + req.getParameter("year")));
			usuario.setSexo(req.getParameter("sex").charAt(0));
			usuario.setNomeDeUsuario(req.getParameter("news"));
			if (!dao.existe(usuario)) {
				dao.adiciona(usuario);
				showSucesso(res.getWriter(), usuario.getEmail());
			}
			else{
				showFalha(res.getWriter());
			}
		}
		catch (SQLException sqle) {
			throw new RuntimeException();
		}
		catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
	public void setUsuarioDAO(UsuarioDAO dao) {
		this.dao = dao;
	}

}
