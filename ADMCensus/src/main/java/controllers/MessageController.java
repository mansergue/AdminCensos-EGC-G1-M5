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
import domain.Message;
import domain.User;
import services.FolderService;
import services.MessageService;
import services.UserService;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	// Services -----------------------------------------------------------

	@Autowired
	private MessageService messageService;

	@Autowired
	private FolderService folderService;

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public MessageController() {
		super();
	}

	// Listing Default
	// -----------------------------------------------------------
	@RequestMapping(value = "/listDefault", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Message> messages = new HashSet<Message>();
		Collection<Folder> folders = new HashSet<Folder>();

		User u = userService.findByPrincipal();
		Assert.notNull(u);
		System.out.println("entra");
		folders = u.getFolders();
		System.out.println("sale");
		Assert.notNull(folders);
		Folder f = folderService.getInbox(u.getUserAccount());
		Assert.notNull(f);
		messages = f.getMessages();
		Assert.notNull(messages);

		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("folders", folders);
		result.addObject("requestURI", "message/list.do");
		result.addObject("isInboxOrSpam", true);

		return result;

	}

	// Listing ---------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int folderId) {
		ModelAndView result;
		Collection<Message> messages = new HashSet<Message>();
		Collection<Folder> folders = new HashSet<Folder>();

		User u= userService.findByPrincipal();
		Assert.notNull(u);
		folders = u.getFolders();
		Folder f = folderService.findOne(folderId);
		Assert.notNull(f);
		boolean isInboxOrSpam = true;
		if (f.getId() == folderService.getOutbox(u.getUserAccount()).getId()) {
			isInboxOrSpam = false;
		}
		messages = f.getMessages();
		Assert.notNull(messages);

		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("folders", folders);
		result.addObject("requestURI", "message/list.do");
		result.addObject("isInboxOrSpam", isInboxOrSpam);

		return result;

	}

	// Send -----------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Message message;
		message = messageService.create();
		result = createSendModelAndView(message);
		return result;
	}

	// Send  --------------------------------------------------------
	@RequestMapping(value = "/send", method = RequestMethod.POST, params = "send")
	public ModelAndView send(@Valid Message message, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			System.out.println(binding);
			result = createSendModelAndView(message);
		} else {
			try {
				messageService.sendMessage(message);
				result = new ModelAndView("redirect:listDefault.do");
			} catch (Throwable oops) {
				result = createSendModelAndView(message, "message.commit.error");
			}
		}

		return result;
	}
	
	
	// Deleted ------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(int messageId) {
		ModelAndView result;

		Message message = messageService.findOne(messageId);
		try {
			if (messageService.checkPrincipalIsRecipient(message)
					|| messageService.checkPrincipalIsSender(message)) {
				messageService.delete(message);
				result = new ModelAndView("redirect:listDefault.do");
			}
			result = new ModelAndView("redirect:listDefault.do");
		} catch (Throwable oops) {
			System.out.println(oops.getMessage());
			result = new ModelAndView("redirect:listDefault.do");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------
	
	protected ModelAndView createSendModelAndView(Message message) {
		ModelAndView result;

		result = createSendModelAndView(message, null);

		return result;
	}

	protected ModelAndView createSendModelAndView(Message mes, String message) {
		ModelAndView result;
		
		User user = mes.getRecipient();
		result = new ModelAndView("message/send");
		result.addObject("mes", mes);
		result.addObject("message", message);
		result.addObject("user", user);
		
		return result;
	}
}
