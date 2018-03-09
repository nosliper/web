package br.quixada.ufc.web.ninjatypr.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.quixada.ufc.web.ninjatypr.model.User;
import br.quixada.ufc.web.ninjatypr.model.dao.UserDAO;
import br.quixada.ufc.web.ninjatypr.service.Security;

@Controller
public class SignupController {
	@Autowired
	private HttpSession session;
	@Autowired
	private UserDAO userDAO;
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("loggedIn", session.getAttribute("user") != null);
		return "signup";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String doSignup(@ModelAttribute @Valid User user, Errors errors, Model model) throws Exception {		
		model.addAttribute("user", user);
		model.addAttribute("loggedIn", session.getAttribute("user") != null);
//		if (errors.hasErrors()) {
//			System.out.println("ERRO");
//			return "signup";
//		}
		if(user != null) {
			User user1 = userDAO.findByUsername(user.getUsername());
			User user2 = userDAO.findByEmail(user.getEmail());
			if (user1 != null || user2 != null) {
				System.out.println("username already taken");
				return "signup";
			}
		}
		user.setPassword(Security.encrypt(user.getPassword()));
		userDAO.save(user);
		session.setAttribute("user", user);
		return "redirect:index";
	}
}
