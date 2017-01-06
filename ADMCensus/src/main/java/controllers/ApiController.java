package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Census;
import services.CensusService;
import services.UserService;

@Controller
@RequestMapping("/API")
public class ApiController extends AbstractController {
	@Autowired
	private CensusService censusService;

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public ApiController() {
		super();
	}

	// Methods ----------------------------------------------------------------

	@RequestMapping(value = "/votesByUser", method = RequestMethod.GET)
	public ModelAndView getVotesByUser(@RequestParam String username) {
		ModelAndView result = new ModelAndView("census/votesByUser");
		Collection<Census> cs;
		cs = censusService
				.findPossibleCensusesByUser(userService.findByUsername(username).getUserAccount().getUsername());
		result.addObject("misVotaciones", true);
		result.addObject("censuses", cs);
		result.addObject("requestURI", "API/votesByUser.do?username=" + username);

		return result;
	}

	@RequestMapping(value = "/getAllCensusByCreador", method = RequestMethod.GET)
	public ModelAndView getAllCensusByCreador(@RequestParam String username) {
		ModelAndView result = new ModelAndView("census/misCensos");
		Collection<Census> cs;
		cs = censusService.findCensusByCreator(userService.findByUsername(username).getUserAccount().getUsername());
		result.addObject("censuses", cs);
		result.addObject("misVotaciones", false);
		result.addObject("requestURI", "API/getAllCensusByCreador.do?username=" + username);

		return result;
	}
	
	
}
