package gr.pr.date_releases.controllers;

import gr.pr.date_releases.entity.UserEntity;
import gr.pr.date_releases.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/{userName}/")
	public String userInfo(@PathVariable("userName") String userName) {
		return "userControlPanel/userInfo";
	}
	
	@RequestMapping("/{userName}/changePassword")
	public String changePassword(@PathVariable("userName") String userName) {
		return "userControlPanel/changeUserPassword";
	}
	
	@RequestMapping("/{userName}/editInfo")
	public String editUserInfo(@PathVariable("userName") String userName) {
		return "userControlPanel/editUserInfo";
	}
	
	@RequestMapping("/createUser")
	public String createUser(@ModelAttribute("user")UserEntity user) {
		
		userService.createUser(user);
		
		return "redirect:/";
	}
}
