package gr.pr.date_releases.controllers;

import gr.pr.date_releases.entity.UserEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String mainMenu() {
		
		return "homePage";
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
	
	//FIXME : authorization needs fix
	@PreAuthorize("!isAuthenticated()")
	@RequestMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}
}
