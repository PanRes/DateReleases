package gr.pr.date_releases.controllers;

import gr.pr.date_releases.entity.UserEntity;
import gr.pr.date_releases.exceptions.DuplicateEmailException;
import gr.pr.date_releases.exceptions.DuplicateUserNameException;
import gr.pr.date_releases.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
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
	
	@RequestMapping("/editInfo")
	public String editUserInfo(Model model) {
		UserEntity user = userService.getLoggedInUser();
		
		model.addAttribute("user",user);
		model.addAttribute("editUserInfo", true);
		
		return "userControlPanel/editUserInfo";
	}
	
	@RequestMapping("updateUser")
	public String updateUser(@ModelAttribute("user") UserEntity user) {
		
		try {
			userService.updateUser(user);
		}
		catch (DuplicateEmailException e) {
			return "redirect:/userPanel/editInfo?duplicateEmail";
		}
		catch (DuplicateUserNameException e) {
			return "redirect:/userPanel/editInfo?duplicateUserName";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/userPanel/editInfo?userUpdateFail";
		}
		
		return "redirect:/userPanel?successEdit";
	}
	
	@RequestMapping("/changePassword")
	public String changePassword(Model model) {
		model.addAttribute("changeUserPassword", true);
		
		return "userControlPanel/changeUserPassword";
	}
	
	//BEST : move to admin controller
	@RequestMapping("/createUser")
	public String createUser(@ModelAttribute("user")UserEntity user) {

		//TODO : handle if fail
		userService.createUser(user);
		
		return "redirect:/";
	}
}
