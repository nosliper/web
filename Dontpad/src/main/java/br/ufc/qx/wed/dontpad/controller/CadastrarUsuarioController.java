package main.java.br.ufc.qx.wed.dontpad.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.br.ufc.qx.wed.dontpad.dao.ConnectionFactory;
import main.java.br.ufc.qx.wed.dontpad.dao.UsuarioDAO;
import main.java.br.ufc.qx.wed.dontpad.model.Usuario;

@WebServlet(name="CadastrarUsuario", urlPatterns="/cadastrar")
public class CadastrarUsuarioController extends MyDontpadController{

	private static final String SUCESSO_AO_CADASTRAR = "Usu&aacute;rio cadastrado com sucesso.";
	private static final String ERRO_AO_CADASTRAR = "N&atilde;o poss&iacute;vel cadastrar os usu&aacute;rio.";
	private static final long serialVersionUID = -1605679868630199122L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioDAO dao = new UsuarioDAO();
		dao.setConexao((new ConnectionFactory()).getConnection());
		Usuario usuario = new Usuario();
		usuario.setEmail(request.getParameter("email"));
		usuario.setNome(request.getParameter("nome") + " " + request.getParameter("sobrenome"));
		usuario.setSenha(request.getParameter("senha"));
		usuario = dao.adiciona(usuario);
		if (usuario != null) {
			sucessoMensagem(SUCESSO_AO_CADASTRAR, request);
		}
		else{
			erroMensagem(ERRO_AO_CADASTRAR, request);
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}



}
