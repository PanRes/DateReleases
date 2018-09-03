package gr.pr.date_releases.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login() {
		return "utils/userUtils/login";
	}
	
	@RequestMapping("/signUp")
	public String signUp() {
		return "utils/userUtils/signUp";
	}
}
