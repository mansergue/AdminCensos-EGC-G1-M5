package controllers;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Folder;
import domain.User;
import services.FolderService;
import services.UserService;

@Controller
@RequestMapping("/folder")
public class FolderController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private FolderService folderService;

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public FolderController() {
		super();
	}
	
	// Edition ----------------------------------------------------------------
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			Folder folder;
			User user = userService.findByPrincipal();
			folder = folderService.create(user);
			result = createEditModelAndView(folder);
			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int folderId) {
			ModelAndView result;
			Folder folder;

			folder = folderService.findOne(folderId);		
			Assert.notNull(folder);
			result = createEditModelAndView(folder);

			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Folder folder, BindingResult binding) {
			ModelAndView result;
			
			if (binding.hasErrors()) {
				System.out.println(binding);
				result = createEditModelAndView(folder);
			} else {
				try {
					folderService.save(folder);			
					result = new ModelAndView("redirect:administration.do");
				} catch (Throwable oops) {
					result = createEditModelAndView(folder, "folder.commit.error");				
				}
			}

			return result;
		}
		
		//Deleted            ------------------------------------------------------
		@RequestMapping(value ="/delete", method=RequestMethod.GET)
		public ModelAndView delete(int folderId){
			ModelAndView result;
			
			Folder folder = folderService.findOne(folderId);
			try{
				folderService.delete(folder);
				result = new ModelAndView("redirect:administration.do");
			} catch(Throwable oops){
				System.out.println(oops.getMessage());
				result = new ModelAndView("redirect:administration.do");
			}
			return result;
		}

	// Administration
	// -----------------------------------------------------------
	@RequestMapping(value = "/administration", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Folder> folders = new HashSet<Folder>();

		User u = userService.findByPrincipal();
		Assert.notNull(u);
		folders = u.getFolders();
		Assert.notNull(folders);

		result = new ModelAndView("folder/list");
		result.addObject("folders", folders);
		result.addObject("requestURI", "folder/list.do");

		return result;

	}
	
	// Ancillary methods ------------------------------------------------------
	
		protected ModelAndView createEditModelAndView(Folder folder) {
			ModelAndView result;

			result = createEditModelAndView(folder, null);
			
			return result;
		}	
		
		protected ModelAndView createEditModelAndView(Folder folder, String message) {
			ModelAndView result;
			
			result = new ModelAndView("folder/edit");
			result.addObject("folder", folder);
			result.addObject("message", message);

			return result;
		}

}
