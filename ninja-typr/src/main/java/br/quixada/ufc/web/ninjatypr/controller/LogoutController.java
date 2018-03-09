package br.quixada.ufc.web.ninjatypr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	@Autowired
	private HttpSession session;
	@RequestMapping("/logout")
	public String doLogout() {
		session.removeAttribute("user");
		return "redirect:login";
	}
}
