package main.java.br.ufc.qx.wed.dontpad.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.br.ufc.qx.wed.dontpad.dao.CadernoDAO;
import main.java.br.ufc.qx.wed.dontpad.dao.ConnectionFactory;
import main.java.br.ufc.qx.wed.dontpad.model.Caderno;
import main.java.br.ufc.qx.wed.dontpad.model.Usuario;

public class CadernoController extends MyDontpadController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8405544168385641521L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CadernoDAO dao = new CadernoDAO();
		dao.setConexao(new ConnectionFactory().getConnection());
		String nome = getNomeCaderno(req);
		Caderno caderno = dao.getCaderno(nome);
		if (caderno == null) {
			Usuario dono = (Usuario) req.getSession().getAttribute("usuario");
			dao.adiciona(new Caderno(nome, dono, true));
		}
		req.getRequestDispatcher("caderno.jsp").forward(req, resp);
	}
	

	public String getNomeCaderno(HttpServletRequest req){
		String path = req.getRequestURL().toString();
		if(path.length() > 2){
			int index = path.indexOf('c') + 1;
			return path.substring(index);
		}
		return "";
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CadernoDAO dao = new CadernoDAO();
		dao.setConexao(new ConnectionFactory().getConnection());
		Caderno caderno = dao.getCaderno(req.getParameter("id"));
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		if (usuario != null) {
			caderno.setPublico(req.getParameter("visibilidade") != "checked");
		}
		if (caderno.getDono().getId() == usuario.getId() || caderno.isPublico()) {
			caderno.setConteudo(req.getParameter("conteudo"));
		}
		dao.salvar(caderno);
		req.getRequestDispatcher("caderno.jsp").forward(req, resp);
	}

}
