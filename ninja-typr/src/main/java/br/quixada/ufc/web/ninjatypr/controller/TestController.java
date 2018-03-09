package br.quixada.ufc.web.ninjatypr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.quixada.ufc.web.ninjatypr.model.SpeedTest;
import br.quixada.ufc.web.ninjatypr.model.User;
import br.quixada.ufc.web.ninjatypr.model.dao.SpeedTestDAO;
import br.quixada.ufc.web.ninjatypr.service.SpeedTestSetup;

@Controller
public class TestController {
	@Autowired
	private HttpSession session;
	@Autowired
	private SpeedTestDAO sptDAO;
	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public String test() {
		return "redirect:index";
	}
	@RequestMapping(value = "/results", method = RequestMethod.POST)
	public String doTest(@RequestParam("correct") String correct,
			@RequestParam("misspelled") String misspelled, @RequestParam("keystrokes") String keystrokes,
			@RequestParam("correctks") String correctKs, Model model) {
		model.addAttribute("loggedIn", session.getAttribute("user") != null);
		SpeedTest test = SpeedTestSetup.setTest(keystrokes, correctKs, correct, misspelled, (User) session.getAttribute("user"));
		model.addAttribute("test", test);
		if (session.getAttribute("user") != null) {
			model.addAttribute("user", session.getAttribute("user"));
			sptDAO.save(test);
		}
		return "results";
	}
}
