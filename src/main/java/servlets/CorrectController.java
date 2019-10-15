package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import controllers.DBUtils;
import dao.UserDAO;
import models.User;
import mySQL.MySQLUserDAO;

@Controller
@RequestMapping("/correct")
public class CorrectController {
	@RequestMapping(method = RequestMethod.GET)
	public String corrGet(HttpSession session, ModelMap model) {

		session = mySession();
		session.getAttribute("user");
		boolean showCorrForm = true;
		model.addAttribute("showCorrForm", showCorrForm);
		return "CorrDB";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String corrPost(@RequestParam(required = false, name = "Login") String login,
			@RequestParam(required = false, name = "Password") String password,
			@RequestParam(required = false, name = "Re_Password") String re_password,
			@RequestParam(required = false, name = "Name") String name,
			@RequestParam(required = false, name = "Region") String region,
			@RequestParam(required = false, name = "Gender") String gender,
			@RequestParam(required = false, name = "Comment") String comment,
			@RequestParam(required = false, name = "Agree") String agree, ModelMap model, HttpSession session) {

		UserDAO userDAO = new MySQLUserDAO();

		boolean isError = false;
		boolean showCorrForm = true;
		StringBuilder error_Text = new StringBuilder();

		model.addAttribute("showForm", showCorrForm);
		model.addAttribute("login", login);

		if (password != null && password.length() == 0) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Password is empty </li>");
		} else {
			if (!DBUtils.isPasswordCorrect(password)) {
				isError = true;
				error_Text.append("<li style = 'color:red'> Not safe Password </li>");
			} else {
				model.addAttribute("password", password);
			}
		}
		if (re_password != null && re_password.length() == 0) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Re_Password is empty </li>");
		}

		if (password != null && password.length() != 0 && re_password != null && re_password.length() != 0) {
			if (!DBUtils.isRe_PasswordCorrect(password, re_password)) {
				isError = true;
				error_Text.append("<li style = 'color:red'> Re_type Password </li>");
			} else {
				model.addAttribute("re_password", re_password);
			}
		}

		if (name != null && name.length() == 0) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Name is empty </li>");
		} else {
			model.addAttribute("name", name);
		}

		if (region != null && region.length() == 0) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Region is empty </li>");
		} else {
			model.addAttribute("region", region);
		}

		if (gender == null) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Gender is empty </li>");
		} else {
			model.addAttribute("gender", Boolean.parseBoolean(gender));
		}

		if (comment != null && comment.length() == 0) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Comment is empty </li>");
		} else {
			model.addAttribute("comment", comment);
		}

		if (agree == null) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Agree is empty </li>");
		} else {
			model.addAttribute("agree", agree);
		}

		if (!isError) {
			showCorrForm = false;
			User corrUser = userDAO.createUser(login, password, name, region, Boolean.parseBoolean(gender), comment);
			userDAO.correctUser(corrUser.getLogin(), corrUser.getPassword(), corrUser.getName(), corrUser.getRegion(),
					corrUser.getGender(), corrUser.getComment());
			session = mySession();
			session.setAttribute("user", corrUser);
			model.addAttribute("showCorrForm", showCorrForm);
		} else {
			model.addAttribute("error", error_Text);
		}

		return "CorrDB";
	}

	public static HttpSession mySession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}
}
