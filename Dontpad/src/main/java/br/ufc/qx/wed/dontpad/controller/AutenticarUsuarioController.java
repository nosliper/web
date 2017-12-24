package main.java.br.ufc.qx.wed.dontpad.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.br.ufc.qx.wed.dontpad.dao.ConnectionFactory;
import main.java.br.ufc.qx.wed.dontpad.dao.UsuarioDAO;
import main.java.br.ufc.qx.wed.dontpad.model.Usuario;

@WebServlet(urlPatterns="/autenticar")
public class AutenticarUsuarioController extends MyDontpadController{

	private static final String FALHA_NA_AUTENTICACAO = "Usu&aacute;rio ou senha inv&aacute;lidos";
	private static final String FLAG_AUTENTIFICADO = "autenticado";
	private static final long serialVersionUID = 1979208128920557545L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.setConexao((new ConnectionFactory()).getConnection());
			Usuario usuario = dao.getUsuario(request.getParameter("email"));
			if (dao.autenticar(usuario.getEmail(), usuario.getSenha())) {
				sucessoMensagem(FLAG_AUTENTIFICADO, request);
				HttpSession sessao = request.getSession();
				sessao.setAttribute("usuario", usuario);
				request.getRequestDispatcher("c/" + usuario.getNome().replace(" ", "-")).forward(request, response);
			}
			else{
				erroMensagem(FALHA_NA_AUTENTICACAO, request);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
