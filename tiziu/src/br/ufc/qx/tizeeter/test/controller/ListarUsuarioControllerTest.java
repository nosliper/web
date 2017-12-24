package br.ufc.qx.tizeeter.test.controller;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import br.ufc.qx.tizeeter.controller.ListarUsuarioController;
import br.ufc.qx.tizeeter.dao.UsuarioDAO;
import br.ufc.qx.tizeeter.model.Usuario;

public class ListarUsuarioControllerTest extends Mockito{
	
	
	@Test
	public void testService() throws IOException, ServletException{
		HttpServletRequest stubHttpServletRequest = mock(HttpServletRequest.class);
		HttpServletResponse stubHttpServletResponse = mock(HttpServletResponse.class);
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		ListarUsuarioController servlet = new ListarUsuarioController();
		
		UsuarioDAO dao = mock(UsuarioDAO.class);
		
		Usuario usuario = new Usuario();
		usuario.setNome("margarida");
		usuario.setSenha("123456");
		usuario.setNomeDeUsuario("flor");
		usuario.setEmail("margarida@gmail.com");
		usuario.setDataDeNascimento(new Date());
		usuario.setEndereco("rua x");
		usuario.setSexo('m');
		usuario.setNovidades(true);
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario);
		when(dao.getTodos()).thenReturn(usuarios);
		when(stubHttpServletResponse.getWriter()).thenReturn(pw);

		servlet.setUsuarioDAO(dao);
		servlet.service(stubHttpServletRequest, stubHttpServletResponse);
		
		String result = sw.getBuffer().toString().trim();
		assertTrue(result.contains("margarida"));
		assertTrue(result.contains("flor"));
		assertTrue(result.contains("margarida@gmail.com"));
		assertTrue(result.contains("rua x"));
	}

}
