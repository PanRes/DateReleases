package gr.pr.date_releases.controllers;

import gr.pr.date_releases.entity.UserEntity;
import gr.pr.date_releases.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userPanel")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping
	public String userInfo(Model model) {
		
		UserEntity user = userService.getLoggedInUser();
		
		model.addAttribute("user",user);
		model.addAttribute("userInfo", true);
		
		return "userControlPanel/userInfo";
	}
	
	@RequestMapping("/changePassword")
	public String changePassword(Model model) {
		model.addAttribute("changeUserPassword", true);
		
		return "userControlPanel/changeUserPassword";
	}
	
	@RequestMapping("/editInfo")
	public String editUserInfo(Model model) {
		model.addAttribute("editUserInfo", true);
		
		return "userControlPanel/editUserInfo";
	}
	
	//BEST : move to admin controller
	@RequestMapping("/createUser")
	public String createUser(@ModelAttribute("user")UserEntity user) {
		
		userService.createUser(user);
		
		return "redirect:/";
	}
}
