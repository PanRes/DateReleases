package gr.pr.date_releases.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public static String login() {
		return "utils/userUtils/";
	}
}
