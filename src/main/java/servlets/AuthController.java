package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import dao.UserDAO;
import models.User;
import mySQL.MySQLUserDAO;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@RequestMapping(method = RequestMethod.GET)
	public String authGet(@RequestParam (required = false, name = "logOut") String logOut,
			HttpSession session) {

		session = mySession();
		
		if (logOut != null) {
			session.invalidate();
			session = mySession();
		}
		return "AuthDB";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String authPOST(@RequestParam(required = false, name = "Login") String login,
			@RequestParam(required = false, name = "Password") String password,
			@RequestParam (required = false, name = "logOut") String logOut,
			HttpSession session, ModelMap model) {

		System.out.println("Logout: " + logOut);
		UserDAO userDAO = new MySQLUserDAO();
		
		boolean showForm = true;
		boolean isError = false;
		session = mySession();
			
		if (logOut != null) {
			session.invalidate();
			session = mySession();
			showForm = true;
		} else {
			if (login != null && password != null) {
				if (userDAO.getAuth(login, password)) {
					showForm = false;
					User user = userDAO.createUser(MySQLUserDAO.userLogin, MySQLUserDAO.userPassword, MySQLUserDAO.userName, 
							MySQLUserDAO.userRegion, MySQLUserDAO.userGender, MySQLUserDAO.userComment);
					session.setAttribute("user", user);
					model.addAttribute("authUser", user);
					model.addAttribute("auth", "Autorized");
					model.addAttribute("showForm",showForm);
					return "AuthedDB";
				} else {
					isError = true;
					model.addAttribute("isError", isError);
					return "AuthDB";
				}
			}
		}
		return "AuthDB";
	}
	
	public static HttpSession mySession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}
	
}
