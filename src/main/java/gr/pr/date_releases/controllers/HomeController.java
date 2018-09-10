package gr.pr.date_releases.controllers;

import gr.pr.date_releases.entity.UserEntity;
import gr.pr.date_releases.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@Autowired
	private SeriesService seriesService;
	
	@RequestMapping("/")
	public String mainMenu(Model model) {
		
		model.addAttribute("allSeries", seriesService.getAllSeries());
		
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
