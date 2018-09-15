package gr.pr.date_releases.controllers;

import gr.pr.date_releases.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String mainMenu() {
		
		return "welcomePage";
	}

	@RequestMapping("/login")
	public String login() {
		return "utils/userUtils/login";
	}
	
	@RequestMapping("/signUp")
	public String signUp(Model model) {
		
		UserEntity user = new UserEntity();
		model.addAttribute("user",user);
		
		return "utils/userUtils/signUp";
	}
}
