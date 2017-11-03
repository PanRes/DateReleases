package gr.pr.datereleases.servlets;

import gr.pr.datereleases.hibernatetools.HibernateTools;
import gr.pr.datereleases.hibernatetools.UserTools;
import gr.pr.datereleases.models.UsersModel;
import gr.pr.datereleases.utils.GenericUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "EditUserInfoServlet", value = "/EditUserInfoServlet")
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
		maxFileSize = 1024*1024*10,
		maxRequestSize = 1024*1024*50)
public class EditUserInfoServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		String success = "fail";
		String button = request.getParameter("submitBtn");
		HttpSession session = request.getSession();
		UsersModel user = (UsersModel) request.getSession().getAttribute("user");

		if (button.equals("Submit Profile Changes")){
			String userName = request.getParameter("userName");
			String email = request.getParameter("email");
			String firstName = request.getParameter("firstName");
			String middleName = request.getParameter("middleName");
			String lastName = request.getParameter("lastName");
			Part profileImg = request.getPart("uploadProfileImage");

			if (UserTools.userNameExists(userName,user.getId())){
				success = "duplicateUserName";
			}
			else if(UserTools.emailExists(email,user.getId())){
				success = "duplicateEmail";
			}
			else {
				user.setUserName(userName);
				user.setEmail(email);
				user.setFirstName(firstName);
				user.setMiddleName(middleName);
				user.setLastName(lastName);

				String userProfileImgsDir = request.getServletContext().getInitParameter("userProfileImgs");
				if(profileImg.getSize() > 0){
					String imgUrl = request.getRealPath("") + userProfileImgsDir;
					File imgFile = GenericUtils.uploadFile(profileImg, new File(imgUrl),user.getId() + "_" + userName + "_");
					user.setImageUrl(userProfileImgsDir + File.separator + imgFile.getName());
				}

				try{
					HibernateTools.updateEntity(user);
					success = "success";
					session.setAttribute("user",user);
				}
				catch (Exception e){
					e.printStackTrace();
				}

			}
		}else{
			user.setImageUrl(null);

			try{
				HibernateTools.updateEntity(user);
				success = "imgRemoved";
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}

		response.sendRedirect("/editUserInfo?success=" + success);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.sendRedirect("/editUserInfo");
	}
}
