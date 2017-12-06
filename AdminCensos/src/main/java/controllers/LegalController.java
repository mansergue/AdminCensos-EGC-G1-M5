/*
 * LegalController.java
 */

package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LegalController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public LegalController() {
		super();
	}

	// About us ---------------------------------------------------------------		

	@RequestMapping("/aboutus")
	public ModelAndView aboutUs() {
		ModelAndView result;
		String companyName;
		String address;
		String vatNumber;
		String contactEmail;

		companyName = "Sample Co, Inc";
		address = "Avenida Reina Mercedes s/n 41012 Sevilla";
		vatNumber = "B12345678";
		contactEmail = "info@sample.com";

		result = new ModelAndView("legal/aboutUs");
		result.addObject("companyName", companyName);
		result.addObject("address", address);
		result.addObject("vatNumber", vatNumber);
		result.addObject("contactEmail", contactEmail);

		return result;
	}

	// About us ---------------------------------------------------------------

	@RequestMapping("/terms")
	public ModelAndView termsOfServices() {
		ModelAndView result;
		String companyName;
		String address;
		String vatNumber;
		String contactEmail;

		companyName = "Sample Co, Inc";
		address = "Avenida Reina Mercedes s/n 41012 Sevilla";
		vatNumber = "B12345678";
		contactEmail = "notice@sample.com";

		result = new ModelAndView("legal/termsOfServices");
		result.addObject("companyName", companyName);
		result.addObject("address", address);
		result.addObject("vatNumber", vatNumber);
		result.addObject("contactEmail", contactEmail);

		return result;
	}

	// About us ---------------------------------------------------------------

	@RequestMapping("/cookies")
	public ModelAndView cookiesPolicy() {
		ModelAndView result;

		result = new ModelAndView("legal/cookiesPolicy");

		return result;
	}

	// About us ---------------------------------------------------------------

	@RequestMapping("/privacy")
	public ModelAndView privacyPolicy() {
		ModelAndView result;
		String companyName;
		String address;
		String contactEmail;

		companyName = "Sample Co, Inc";
		address = "Avenida Reina Mercedes s/n 41012 Sevilla";
		contactEmail = "lopd@sample.com";

		result = new ModelAndView("legal/privacyPolicy");
		result.addObject("companyName", companyName);
		result.addObject("address", address);
		result.addObject("contactEmail", contactEmail);

		return result;
	}

}
