package br.quixada.ufc.web.ninjatypr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/about")
	public String doAbout(Model model) {
		model.addAttribute("loggedIn", session.getAttribute("user") != null);
		model.addAttribute("user", session.getAttribute("user"));
		return "about";
	}
	
}
