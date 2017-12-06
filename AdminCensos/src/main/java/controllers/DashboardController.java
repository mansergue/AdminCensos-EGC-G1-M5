package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.CensusService;
import controllers.AbstractController;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AbstractController{
	// Services ---------------------------------------------------------------
	@Autowired
	private CensusService censusService;
	

	// Constructors -----------------------------------------------------------
	public DashboardController(){
		super();
	}
		
	// Listing ----------------------------------------------------------------	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result=new ModelAndView("census/dashboard");
		
		
		int openCensuses=censusService.openCensuses();
		String censusMoreVotes = censusService.censusMoreVotes();
		String censusLessVotes = censusService.censusLessVotes();
		
		
		result.addObject("openCensuses", openCensuses);	
		result.addObject("censusMoreVotes", censusMoreVotes);	
		result.addObject("censusLessVotes", censusLessVotes);	
		
		return result;
	}

}
