package gr.pr.date_releases.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping("/403")
	public String accessDenied() {
		return "/errorPages/403";
	}
	
	/*
	public String notFound() {
		return "/errorPages/404";
	}
	*/
}
