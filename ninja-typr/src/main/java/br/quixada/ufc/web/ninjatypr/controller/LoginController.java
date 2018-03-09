package br.quixada.ufc.web.ninjatypr.controller;

import javax.servlet.http.HttpSession;
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
public class LoginController {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private HttpSession session;
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("loggedIn", session.getAttribute("user") != null);
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(@ModelAttribute User user, Model model, Errors errors) throws Exception {
		model.addAttribute("user", new User());
		model.addAttribute("loggedIn", session.getAttribute("user") != null);
//		if (errors.hasErrors()){
//			return "login";
//		}
		//TODO: validate the xit out, fix the issue with spring trying to validate the email field
		if(Security.authenticate(userDAO, user)) {
			session.setAttribute("user", userDAO.findByUsername(user.getUsername()));
			return "redirect:index";
		}
		return "redirect:login";
		
	}
}
