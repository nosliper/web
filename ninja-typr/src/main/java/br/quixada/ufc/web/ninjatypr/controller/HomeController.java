package br.quixada.ufc.web.ninjatypr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.quixada.ufc.web.ninjatypr.model.User;

@Controller
public class HomeController {
	@Autowired
	private HttpSession session;
	@RequestMapping("/index")
	public String home(Model model) {
		return index(model);
	}
	@RequestMapping("/")
	public String index(Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("loggedIn", user != null);
		model.addAttribute("title", "ninja typr");
		return "index";
	}
}
