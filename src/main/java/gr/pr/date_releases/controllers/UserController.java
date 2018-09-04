package gr.pr.date_releases.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/{userName}")
public class UserController {
	
	@RequestMapping("/")
	public String userInfo(@PathVariable("userName") String userName) {
		return "userControlPanel/userInfo";
	}
	
	@RequestMapping("/changePassword")
	public String changePassword(@PathVariable("userName") String userName) {
		return "userControlPanel/changeUserPassword";
	}
	
	@RequestMapping("/editInfo")
	public String editUserInfo(@PathVariable("userName") String userName) {
		return "userControlPanel/editUserInfo";
	}
	
}
