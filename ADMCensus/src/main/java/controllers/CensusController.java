package controllers;

import java.io.FileOutputStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import domain.Census;
import domain.User;
import domain.Vote;
import services.CensusService;
import services.UserService;
import services.VoteService;
import utilities.RESTClient;

@Controller
@RequestMapping("/census")
public class CensusController extends AbstractController {

	@Autowired
	private CensusService censusService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VoteService voteService;

	// Constructors -----------------------------------------------------------

	public CensusController() {
		super();
	}

	/****
	 * Metodos externos
	 ******/

	// Create census ----------------------------------------------------------
	// Recibe parametros de votacion y crea un censo por votación

	@RequestMapping(value = "/create", method = RequestMethod.GET,produces = "application/json")
	public ModelAndView create() throws ParseException {
		
		ModelAndView result = new ModelAndView("census/edit");
		voteService.popularVotaciones();
		Collection<Vote> votes= voteService.findAll();
		Census census = censusService.create();
		
		result.addObject("census", census);
		result.addObject("votes", votes);
		
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save",produces = "application/json")
	public ModelAndView create(@Valid Census census, BindingResult binding){
		System.out.println("asasasasas");
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding);
			result = createModelAndView(census);
		} else {
			try {
				System.out.println("entra al try");
				User user = userService.findByPrincipal();
				Vote vote = voteService.findVoteByTitle(census.getTitle());
				System.out.println(vote.getFechaCierre());
				census.setEndDate(vote.getFechaCierre());
				census.setIdVotacion(vote.getIdVotacion());
				census.setPostalCode(vote.getCp());
				census.setStartDate(vote.getFechaCreacion());
				census.setUsernameCreator(user.getUserAccount().getUsername());
				censusService.save(census);				
				result = new ModelAndView("redirect:getAllCensusByCreador.do");
			} catch (Throwable oops) {
				result = createModelAndView(census, "census.commit.error");				
			}
		}
		return result;
	}

	// Devuelve JSon a a votaciones para saber si pueden borrar una votación
	// En caso afirmativo, el censo se borrará automáticamente al dar una
	// respuesta positiva -----------------------------------------------------

	@RequestMapping(value = "/canDelete", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String canDelete(@RequestParam int idVotacion, @RequestParam String username) {
		return censusService.canDelete(idVotacion, username);
	}

// Devuelve JSon a cabina para saber si un usuario puede votar ------------

	@RequestMapping(value = "/canVote", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String canVote(@RequestParam int idVotacion, @RequestParam String username) throws ParseException {
		voteService.popularVotaciones();
		return censusService.canVote(idVotacion, username);
	}

	// Actualiza el estado de un usuario en una votaciÃ³n por cabina -----------

	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public @ResponseBody String updateUser(@RequestParam int idVotacion, @RequestParam String tipoVotacion,@RequestParam String username) throws ParseException {
		voteService.popularVotaciones();
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
	
	// Devuelve un censo con sus usuarios para deliberaciones al preguntar por
	// una votaci�n

	@RequestMapping(value = "/findCensusByVote", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Census findCensusByVote(@RequestParam int idVotacion) {
		return censusService.findCensusByVote(idVotacion);
	}

	/*****
	 * Métodos internos
	 ******/

	// Método para la vista de votaciones por usuario -------------------------

		@RequestMapping(value = "/votesByUser", method = RequestMethod.GET)
		public ModelAndView getVotesByUser() {
			ModelAndView result = new ModelAndView("census/votesByUser");
			Collection<Census> cs;
			cs = censusService.findPossibleCensusesByUser(userService.findByPrincipal().getUserAccount().getUsername());
			result.addObject("misVotaciones", true);
			result.addObject("censuses", cs);
			result.addObject("requestURI", "census/votesByUser.do");

			return result;
		}

	// Metodo para la vista de censos por creador -----------------------------
		//Defensa
	@RequestMapping(value = "/getAllCensusByCreador", method = RequestMethod.GET)
	public ModelAndView getAllCensusByCreador() {
		ModelAndView result = new ModelAndView("census/misCensos");
		Collection<Census> cs;
		
		cs = censusService.findCensusByCreator(userService.findByPrincipal().getUserAccount().getUsername());
		
		result.addObject("censuses", cs);
		result.addObject("misVotaciones", false);
		result.addObject("requestURI", "census/getAllCensusByCreador.do");
		
		return result;
	}

	// Añadir usuarios a un censo cerrado, como administrador del censo -------

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUser(@RequestParam int censusId,@RequestParam String usernameAdd) {
		ModelAndView result = new ModelAndView("census/misVotaciones");
		try {
			censusService.addUserToClosedCensus(censusId, userService.findByPrincipal().getUserAccount().getUsername(), usernameAdd);
			result = new ModelAndView("redirect:/census/edit.do?censusId=" + censusId);

		} catch (Exception oops) {
			result = new ModelAndView("redirect:/census/edit.do?censusId=" + censusId);
			result.addObject("message", "error");
			JOptionPane.showMessageDialog(null, "Error");
			oops.getStackTrace();
		}

		return result;
	}

	// Registrarse en un censo abierto y activo -------------------------------

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET)
	public ModelAndView addUser(@RequestParam int censusId) {
		ModelAndView result = null;
		try {

			censusService.addUserToOpenedCensus(censusId, userService.findByPrincipal().getUserAccount().getUsername());
			result = new ModelAndView("redirect:/census/getCensusesToRegister.do");

		} catch (Exception oops) {
			result = new ModelAndView("redirect:/census/getCensusesToRegister.do");
			result.addObject("message", "error");
			JOptionPane.showMessageDialog(null, "Error");
			oops.getStackTrace();
		}

		return result;
	}

	// Remove Users -----------------------------------------------------------

	@RequestMapping(value = "/removeUser", method = RequestMethod.GET)
	public ModelAndView removeUser(@RequestParam int censusId, @RequestParam String usernameRemove) {
		ModelAndView result = null;
		try {

			censusService.removeUserOfClosedCensus(censusId, userService.findByPrincipal().getUserAccount().getUsername(), usernameRemove);

			result = new ModelAndView("redirect:/census/edit.do?censusId=" + censusId);

		} catch (Exception oops) {
			result = new ModelAndView("redirect:/census/edit.do?censusId=" + censusId);
			result.addObject("message", "error");
			JOptionPane.showMessageDialog(null, "Error");
			oops.getStackTrace();
		}

		return result;
	}

	// Encontrar un usuario mediante un keyword -------------------------------

	@RequestMapping(value = "/searchByUsername", method = RequestMethod.GET)
	public ModelAndView findUser(@RequestParam String usernameSearch, @RequestParam int censusId) {
		ModelAndView result;
		String requestUri = "census/searchByUsername.do?username=" + usernameSearch;

		Collection<String> usernames = censusService.findByUsername(usernameSearch, censusId);

		Census census = censusService.findOne(censusId);
		Date now = new Date();

		Boolean editable= census.getTipo().equals("cerrado") && census.getUsernameCreator().equals(userService.findByPrincipal().getUserAccount().getUsername())&& census.getEndDate().after(now);

		Collection<String> userList = census.getVotoPorUsuario().keySet();

		result = new ModelAndView("census/manage");
		result.addObject("requestURI", requestUri);
		result.addObject("usernames", usernames);
		result.addObject("census", census);
		result.addObject("user", userList);
		result.addObject("editable", editable);
		return result;
	}
	
//	// Encontrar censos por comunidades aut�nomas -------------------------------
//
//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public ModelAndView search(@RequestParam String autonomousCommunitySearch) {
//		ModelAndView result;
//		String requestUri = "census/search.do?autonomousCommunity=" + autonomousCommunitySearch;
//
//		
//		Collection<Census> censusByAutonomousCommunity=censusService.findByAutonomousCommunity(autonomousCommunitySearch);
//		Set<String> autonomousComunities=new HashSet<String>();
//		for(Census c: censusByAutonomousCommunity){
//			autonomousComunities.add(c.getComunidadAutonoma());
//		}
//		result=new ModelAndView("census/list");
//		result.addObject("requestURI", requestUri);
//		result.addObject("autonomousComunities", autonomousComunities);
//		result.addObject("censuses",censusByAutonomousCommunity);
//		
//		return result;
//	}

	// Detalles del censo -----------------------------------------------------

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView details(@RequestParam int censusId) {
		ModelAndView result;
		Date now = new Date();
		Census census = censusService.findOne(censusId);
		result = createEditModelAndView(census);
		Boolean editable;
		editable = census.getTipo().equals("cerrado") && census.getUsernameCreator().equals( userService.findByPrincipal().getUserAccount().getUsername())&& census.getEndDate().after(now);

		result.addObject("editable", editable);
		return result;
	}

	// Editar un censo para añadir o quitar usuarios y buscarlos --------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int censusId) {
		ModelAndView result = new ModelAndView("census/manage");

		Date now = new Date();
		Boolean editable;

		// Llamada a todos los usuarios del sistema

		Map<String, String> usernamesAndEmails = RESTClient.getMapUSernameAndEmailByJsonAutentication();
		Census census = censusService.findOneByCreator(censusId, userService.findByPrincipal().getUserAccount().getUsername());

		Collection<String> userList = census.getVotoPorUsuario().keySet();
		editable = census.getTipo().equals("cerrado") && census.getUsernameCreator().equals(userService.findByPrincipal().getUserAccount().getUsername())&& census.getEndDate().after(now);
		result.addObject("usernames", usernamesAndEmails.keySet());
		result.addObject("census", census);
		result.addObject("user", userList);
		result.addObject("editable", editable);
		result.addObject("requestURI", "census/edit.do");

		return result;
	}

	// Exportar un censo a .txt -----------------------------------------------

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public ModelAndView export(@RequestParam int censusId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView result;
		try {
			Census census = censusService.findOne(censusId);

			OutputStream out = response.getOutputStream();
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"Report" + ".txt\";");
			response.setHeader(headerKey, headerValue);

			String s0 = "\nDetails of the census\n\n";
			String s1 = "Owner: " + census.getUsernameCreator() + "\n";
			String s2 = "Name of vote: " + census.getTitle() + "\n";
			String s3 = "Vote number: " + census.getIdVotacion() + "\n";
			String s4 = "Start date: " + census.getStartDate() + "\n";
			String s5 = "Finish date: " + census.getEndDate() + "\n";
			String s6 = "Postal code: " + census.getPostalCode() + "\n";
			String s7 = "Autonomous community: " + census.getComunidadAutonoma() + "\n";
			String s8 = "---------------------" + "\n\n";
			String s9 = "Voters: \n";

			// Aquí estamos dando el formato que queremos que tenga nuestro
			// .txt
			out.write(s0.getBytes(StandardCharsets.UTF_8));
			out.write(s1.getBytes(StandardCharsets.UTF_8));
			out.write(s2.getBytes(StandardCharsets.UTF_8));
			out.write(s3.getBytes(StandardCharsets.UTF_8));
			out.write(s4.getBytes(StandardCharsets.UTF_8));
			out.write(s5.getBytes(StandardCharsets.UTF_8));
			out.write(s6.getBytes(StandardCharsets.UTF_8));
			out.write(s7.getBytes(StandardCharsets.UTF_8));
			out.write(s8.getBytes(StandardCharsets.UTF_8));
			out.write(s9.getBytes(StandardCharsets.UTF_8));

			// Todos los usuarios del sistema

			Map<String, String> mapUsers = RESTClient.getMapUSernameAndEmailByJsonAutentication();
			Collection<String> usernames = mapUsers.keySet();

			// Todos los que han votado en un censo

			Collection<String> userList = census.getVotoPorUsuario().keySet();
			if (usernames.size() != 0) {

				// Aquí compruebo que de todos los usuarios disponibles en el
				// sistema, cuál de
				// ellos ha votado ya en el censo

				for (String aux : usernames) {
					for (String voter : userList) {

						// Uno de los usuarios del sistema ya ha votado en dicho
						// censo

						if (aux.equals(voter)) {

							// Obtenemos el mapa del censo, para saber si el
							// usuario
							// ha votado o no

							HashMap<String, Boolean> map = census.getVotoPorUsuario();

							// Añadimos este usuario que ya ha votado al text
							User user = RESTClient.getCertainUserByJsonAuthentication(voter);

							String s10 = "\nUser_Id: " + user.getId() + "\n";
							String s11 = "Name: " + user.getName() + "\n";
							String s12 = "Surname: " + user.getSurname() + "\n";
							String s13 = "Username: " + user.getUserAccount().getUsername() + "\n";
							String s14 = "Email: " + user.getEmail() + "\n";
							String s15 = "Genre: " + user.getGenre() + "\n";
							String s16 = "Autonomous community: " + user.getAutonomous_community() + "\n";
							String s17 = "Age: " + user.getAge() + "\n";
							String s18 = "Has voted?: " + map.get(voter) + "\n";
							String s19 = "*****************\n";

							out.write(s10.getBytes(StandardCharsets.UTF_8));
							out.write(s11.getBytes(StandardCharsets.UTF_8));
							out.write(s12.getBytes(StandardCharsets.UTF_8));
							out.write(s13.getBytes(StandardCharsets.UTF_8));
							out.write(s14.getBytes(StandardCharsets.UTF_8));
							out.write(s15.getBytes(StandardCharsets.UTF_8));
							out.write(s16.getBytes(StandardCharsets.UTF_8));
							out.write(s17.getBytes(StandardCharsets.UTF_8));
							out.write(s18.getBytes(StandardCharsets.UTF_8));
							out.write(s19.getBytes(StandardCharsets.UTF_8));

							break;
						}
					}
				}
			} else {
				out.write("Nothing to display because there isn't any voters".getBytes(StandardCharsets.UTF_8));
			}

		} catch (IOException de) {
			throw new IOException(de.getMessage());
		}

		// obtains response's output stream
		OutputStream outStream = response.getOutputStream();
		outStream.close();

		result = new ModelAndView("redirect:getAllCensusByCreador.do");
		return result;
	}

	// Exportar un censo a .pdf -----------------------------------------------

	@RequestMapping(value = "/exportPDF", method = RequestMethod.GET)
	public ModelAndView exportPDF(@RequestParam int censusId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DocumentException {
		response.setContentType("application/pdf");
		ModelAndView result;
		try {
			Census census = censusService.findOne(censusId);

			Document documento = new Document();
			PdfWriter.getInstance(documento, response.getOutputStream());
			documento.open();
			// Aquí estamos dando el formato que queremos que tenga nuestro
			// .pdf

			documento.add(new Paragraph("Details of the census" + "\n" + "\n"));
			documento.add(new Paragraph("Owner: " + census.getUsernameCreator() + "\n"));
			documento.add(new Paragraph("Name of vote: " + census.getTitle() + "\n"));
			documento.add(new Paragraph("Vote number: " + census.getId() + "\n"));
			documento.add(new Paragraph("Start date: " + census.getStartDate() + "\n"));
			documento.add(new Paragraph("Finish date: " + census.getEndDate() + "\n"));
			documento.add(new Paragraph("Postal code: " + census.getPostalCode() + "\n"));
			documento.add(new Paragraph("Autonomous community: " + census.getComunidadAutonoma() + "\n"
					+ "---------------------" + "\n" + "\n"));
			documento.add(new Paragraph("Voters: " + "\n"));

			// Todos los usuarios del sistema

			Map<String, String> mapUsers = RESTClient.getMapUSernameAndEmailByJsonAutentication();
			Collection<String> usernames = mapUsers.keySet();

			// Todos los que han votado en un censo

			Collection<String> userList = census.getVotoPorUsuario().keySet();
			if (usernames.size() != 0) {

				// Aquí compruebo que de todos los usuarios disponibles en el
				// sistema, cuál de
				// ellos ha votado ya en el censo

				for (String aux : usernames) {
					for (String voter : userList) {

						// Uno de los usuarios del sistema ya ha votado en dicho
						// censo

						if (aux.equals(voter)) {

							// Obtenemos el mapa del censo, para saber si el
							// usuario
							// ha votado o no

							HashMap<String, Boolean> map = census.getVotoPorUsuario();

							// Añadimos este usuario que ya ha votado al text

							User user = RESTClient.getCertainUserByJsonAuthentication(voter);

							documento.add(new Paragraph("\n" + "User_Id: " + user.getId() + "\n"));
							documento.add(new Paragraph("Username: " + user.getUserAccount().getUsername() + "\n"));
							documento.add(new Paragraph("Name: " + user.getName() + "\n"));
							documento.add(new Paragraph("Surname: " + user.getSurname() + "\n"));
							documento.add(new Paragraph("Email: " + user.getEmail() + "\n"));
							documento.add(new Paragraph("Genre: " + user.getGenre() + "\n"));
							documento.add(
									new Paragraph("Autonomous community: " + user.getAutonomous_community() + "\n"));
							documento.add(new Paragraph("Age: " + user.getAge() + "\n"));
							documento.add(
									new Paragraph("Has voted?: " + map.get(voter) + "\n" + "*****************" + "\n"));
							System.out.println("fin de escritura del PDF");
							break;
						}
					}
				}
			} else {
				documento.add(new Paragraph("Nothing to display because there isn't any voters"));
			}

			documento.close();
		} catch (DocumentException de) {
			throw new IOException(de.getMessage());
		}

		result = new ModelAndView("redirect:getAllCensusByCreador.do");
		return result;
	}

	// Save -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Census census, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(census);
		} else {
			try {
				censusService.save(census);
				result = new ModelAndView("redirect:/census/list.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(census, "census.commit.error");
			}
		}

		return result;
	}

	// Nos devuelve una lista con los censos en los que nos podemos registrar -

	@RequestMapping(value = "/getCensusesToRegister", method = RequestMethod.GET)
	public ModelAndView getCensusesToRegister() {
		ModelAndView result = new ModelAndView("census/censosARegistrar");
		Collection<Census> censuses = new ArrayList<Census>();
		censuses = censusService.findCensusesToRegisterByUser(userService.findByPrincipal().getUserAccount().getUsername());
		result.addObject("censuses", censuses);
		result.addObject("misVotaciones", false);
		result.addObject("requestURI", "census/getCensusesToRegister.do");

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Census census) {
		ModelAndView result;

		result = createEditModelAndView(census, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Census census, String message) {
		ModelAndView result = new ModelAndView("census/create");

		if (census.getId() != 0) {
			result = new ModelAndView("census/details");
		}
		HashMap<String, Boolean> mapa = census.getVotoPorUsuario();
		result.addObject("census", census);
		result.addObject("mapa", mapa);
		result.addObject("message", message);

		return result;

	}
	
	protected ModelAndView createModelAndView(Census census){
		ModelAndView result;
		result = createModelAndView(census, null);
		return result;
	}
	
	protected ModelAndView createModelAndView(Census census, String message) {
		ModelAndView result = new ModelAndView("census/edit");
		Collection <Vote> votes= voteService.findAll();
		result.addObject("census", census);
		result.addObject("message", message);
		result.addObject("votes", votes);
		return result;

	}

}

