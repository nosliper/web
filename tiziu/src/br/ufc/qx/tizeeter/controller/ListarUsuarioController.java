package br.ufc.qx.tizeeter.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import br.ufc.qx.tizeeter.dao.UsuarioDAO;
import br.ufc.qx.tizeeter.model.ConnectionFactory;
import br.ufc.qx.tizeeter.model.Usuario;

@WebServlet(name = "ListarUsuarioController", urlPatterns = "/usuario/todos")
public class ListarUsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 2L;
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		UsuarioDAO dao = new UsuarioDAO(new ConnectionFactory());
		
	}
	public void setUsuarioDAO(UsuarioDAO dao) {
		
	}
	
	

}

