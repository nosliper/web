package br.ufc.quixada.webdev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/register", name = "SignUpServlet")
public class SignUpServlet extends HttpServlet{
	private static final long serialVersionUID = 55558549849484984L;
	private void showInfo(PrintWriter out, User user){
		String open = "<html><head><title>Cadastro</title></head><body>";
		String close = "</body></html>";
		out.println(open);
		out.println(close);
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User(
				req.getParameter("username"),
				req.getParameter("fullName"),
				req.getParameter("email"),
				req.getParameter("birth"),
				req.getParameter("password"),
				req.getParameter("address"),
				req.getParameter("gender"),
				req.getParameter("receiveFeeds") == "yes"? true : false);
		showInfo(resp.getWriter(), user);
	}
}
