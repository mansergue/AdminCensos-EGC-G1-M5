package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// public static String username;
	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required = false, defaultValue = "no_logged") String token) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		String username;
		String password;

		username = token.split(":")[0];
		password = token.split(":")[1];
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");
		result.addObject("name", username);
		result.addObject("moment", moment);
		result.addObject("username", username);
		result.addObject("password", password);
		result.addObject("token", token);

		return result;
	}
}