package br.ufc.qx.webdev;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = "/sessions", name = "SessionServlet")
public class SesssionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest red, HttpServletResponse resp) throws ServletException, IOException {
		//TODO
	}
	
}
