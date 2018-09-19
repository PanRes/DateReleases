package gr.pr.date_releases.controllers.advice;

import gr.pr.date_releases.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UserControllerAdvice {
	
	@Autowired
	private UserService userService;
	
/*
	@ModelAttribute("userLogged")
	public UserEntity getLoggedInUser() {
		return userService.getLoggedInUserName();
	}
*/
}
