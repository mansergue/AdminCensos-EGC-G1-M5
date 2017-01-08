package controllers;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.User;
import domain.Validator;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	// Supporting services -----------------------

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserDetailsService userDetailsService;

	// Constructors -----------------------------------------------------------

	public UserController() {
		super();
	}

	// Login
	// ------------------------------------------------------------------------

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView result;
		Authority authority = new Authority();
		UserAccount userAccount = new UserAccount();

		authority.setAuthority("USER");
		userAccount.addAuthority(authority);
		result = new ModelAndView("user/login");
		result.addObject("userAccount", userAccount);

		return result;
	}

	@RequestMapping("/entry")
	public ModelAndView entry(@Valid UserAccount userAccount, BindingResult bindingResult, HttpServletRequest request)
			throws IOException {
		ModelAndView result = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String token;
		Authority authority = new Authority();

		authority.setAuthority("USER");
		userAccount.addAuthority(authority);

		Validator validator;
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			result = new ModelAndView("user/login");
			result.addObject("message", "entry.error");
			System.out.println("Error");

		} else {
			token = loginService.contructToken(userAccount);
			validator = objectMapper.readValue(new URL("https://autha.agoraus1.egc.duckdns.org/api/index.php?method=checkToken&token=" + token),Validator.class);

			if (validator.isValid()) {
				System.out.println("Válida");

				try {
					Md5PasswordEncoder md5 = new Md5PasswordEncoder();
					DaoAuthenticationProvider authenticator = new DaoAuthenticationProvider();
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userAccount.getUsername(), md5.encodePassword(userAccount.getPassword(), null));

					authenticationToken.setDetails(new WebAuthenticationDetails(request));
					authenticator.setUserDetailsService(userDetailsService);
					Authentication authentication = authenticator.authenticate(authenticationToken);

					SecurityContextHolder.getContext().setAuthentication(authentication);

				} catch (Exception e) {
					User user = objectMapper.readValue(new URL("https://autha.agoraus1.egc.duckdns.org/api/index.php?method=getUser&user="+ userAccount.getUsername()),User.class);

					DaoAuthenticationProvider authenticator = new DaoAuthenticationProvider();
					authenticator.setUserDetailsService(userDetailsService);

					userAccount.setPassword(new Md5PasswordEncoder().encodePassword(userAccount.getPassword(), null));
					user.setUserAccount(userAccount);

					user = userService.save(user);

					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userAccount.getUsername(), userAccount.getPassword(), null);
					authenticationToken.setDetails(new WebAuthenticationDetails(request));
					Authentication authentication = authenticator.authenticate(authenticationToken);

					SecurityContextHolder.getContext().setAuthentication(authentication);

				}

				result = new ModelAndView("welcome/index");

			} else {
				System.out.println("No válida");
				result = new ModelAndView("user/login");
				result.addObject("message", "entry.error");

			}
		}

		return result;

	}

}