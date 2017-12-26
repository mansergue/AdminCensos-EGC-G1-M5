
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.CensusService;


import controllers.AbstractController;
import org.springframework.util.Assert;
import domain.Census;



@Controller
@RequestMapping("/probando")
public class ProbandoController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public ProbandoController() {
		super();
	}


	// Services ---------------------------------------------------------------
	@Autowired
	private CensusService censusService;

	// Creation --------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET, params = "curriculaId")
	public ModelAndView create(@RequestParam int curriculaId, RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Census census ;

		census=censusService.create();
		
		result = this.createEditModelAndView(census);
		return result;
	}

	// Listing ----------------------------------------------------------------
	
	// Edition ----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int censusId, RedirectAttributes redirecAttrs) {
		ModelAndView view;
		Census census;

		census = this.censusService.findOne(censusId);

		view = this.createEditModelAndView(census);

		return view;
	}
	

/*
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Census census, BindingResult binding, RedirectAttributes redirectAttrs) {
		ModelAndView view;

		try {
			census = this.educationRecordService.reconstruct(educationRecord, educationRecord.getCurricula(), binding);
			if (binding.hasErrors())
				view = this.createEditModelAndView(educationRecord);
			else {
				this.educationRecordService.save(educationRecord);
				view = new ModelAndView("redirect:/curricula/candidate/myDisplay.do?curriculaId="+educationRecord.getCurricula().getId());
				redirectAttrs.addFlashAttribute("message", "actor.commit.ok");
			}
		} catch (Throwable opps) {
			if (binding.hasErrors())
				view = this.createEditModelAndView(educationRecord,"actor.commit.error" );
			else
				view = this.createEditModelAndView(educationRecord, "actor.commit.error");
		}

		return view;
	}
*/
	
	

	// Delete ----------------------------------------------------------------


	// Ancillary methods ------------------------------------------------------


	public ModelAndView createEditModelAndView(Census census) {
		return this.createEditModelAndView(census, null);
	}

	public ModelAndView createEditModelAndView(Census census, String message) {
		ModelAndView view;

		view = new ModelAndView("census/edit");
		//view.addObject("requestParam", "educationRecord/candidate/edit.do");
		//view.addObject("cancelParam", "curricula/candidate/display.do");
		view.addObject("message", message);
		view.addObject("census", census);

		return view;
	}
}
