package br.quixada.ufc.web.ninjatypr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.quixada.ufc.web.ninjatypr.model.User;
import br.quixada.ufc.web.ninjatypr.model.dao.UserDAO;
import br.quixada.ufc.web.ninjatypr.service.UserStats;

@Controller
public class StatsController {
	@Autowired
	private HttpSession session;
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping("/stats/{username}")
	public String stats(@PathVariable String username, Model model) {
		model.addAttribute("loggedIn", session.getAttribute("user") != null);
		model.addAttribute("user", session.getAttribute("user"));
		User user = userDAO.findByUsername(username);
		if (session.getAttribute("user") == null || user == null) {
			return "index";
			//TODO: make an 'user not found' view
			//TODO: make a 'you must be logged' stuff
		}
		UserStats stats = new UserStats(user);
		model.addAttribute("stats", stats);
		return "stats";
	}
}
