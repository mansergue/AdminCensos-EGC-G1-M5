/*package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import domain.Census;
import services.CensusService;
import services.UserService;

@Controller
@RequestMapping("/api")
public class ApiController extends AbstractController {
	@Autowired
	private CensusService censusService;

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public ApiController() {
		super();
	}

	@RequestMapping(value = "/methods", method = RequestMethod.GET)
	public ModelAndView methods() {
		ModelAndView result = new ModelAndView("census/api");

		int openCensuses = censusService.openCensuses();

		result.addObject("openCensuses", openCensuses);

		return result;
	}

	// Methods ----------------------------------------------------------------

	// Devuelve JSon a cabina para saber si un usuario puede votar —----------

	@RequestMapping(value = "/canVote", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String canVote(@RequestParam int idVotacion, @RequestParam String username) {
		// Llamar a todas las votaciones de recuento y las que falten meterlas
		// en nuestra base de datos
		return censusService.canVote(idVotacion, username);
	}

	// Actualiza el estado de un usuario en una votación por cabina —---------

	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public @ResponseBody String updateUser(@RequestParam int idVotacion, @RequestParam String tipoVotacion,
			@RequestParam String username) {
		try {
			if (censusService.updateUser(idVotacion, tipoVotacion, username)) {
				return new String("{\"result\":\"yes\"}");
			} else {
				return new String("{\"result\":\"no\"}");
			}

		} catch (Exception oops) {
			return new String("{\"result\":\"no\"}");
		}

	}

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

	@RequestMapping(value = "/getCensusesToRegister", method = RequestMethod.GET)
	public ModelAndView getCensusesToRegister(@RequestParam String username) {
		ModelAndView result = new ModelAndView("census/censosARegistrar");
		Collection<Census> censuses = new ArrayList<Census>();
		censuses = censusService
				.findCensusesToRegisterByUser(userService.findByUsername(username).getUserAccount().getUsername());
		result.addObject("censuses", censuses);
		result.addObject("misVotaciones", false);
		result.addObject("requestURI", "API/getCensusesToRegister.do?username=" + username);

		return result;
	}

}
*/