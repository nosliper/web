package br.quixada.ufc.web.ninjatypr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.quixada.ufc.web.ninjatypr.model.User;
import br.quixada.ufc.web.ninjatypr.model.dao.UserDAO;

@Controller
public class EditController {
//	@Autowired
//	private User user;
//	@Autowired
//	private UserDAO userDAO;
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/edit/{username}", method = RequestMethod.GET)
	public String edit (@PathVariable String username, Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("loggedIn", session.getAttribute("user") != null); //TODO: should I use cast here?
		return "edit";
	}
}
