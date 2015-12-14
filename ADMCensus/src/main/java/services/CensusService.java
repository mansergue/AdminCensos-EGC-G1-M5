package services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CensusRepository;
import utilities.Gmail;
import utilities.RESTClient;
import domain.Census;

@Service
@Transactional
public class CensusService {

	// Atributo necesario para mandar email con la modificación del censo
	private static String cuerpoEmail = "";

	// Managed repository -----------------------------------------------------

	@Autowired
	private CensusRepository censusRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public CensusService() {
		super();
	}

	// Others Methods

	/**
	 *
	 * Método que crea un censo a partir de una votacion
	 *
	 * @param idVotacion
	 *            Identificador de la votacion
	 * @param username
	 *            Nombre de usuario que ha creado la votacion
	 * @param fecha_inicio
	 *            Fecha de inicio para la votacion
	 * @param fecha_fin
	 *            Fecha de fin para la votacion
	 * @param tituloVotacion
	 *            Cadena de texto con el titulo de la votacion
	 * @param tipoVotacion
	 *            Cadena de texto con el tipo de la votacion (abierta o cerrada)
	 * @return census
	 * @throws ParseException
	 * 
	 */
	public Census create(int idVotacion, String username, String fecha_inicio, String fecha_fin, String tituloVotacion,
			String tipoVotacion) throws ParseException {
		Assert.isTrue(!username.equals(""));
		Assert.isTrue(tipoVotacion.equals("abierta") || tipoVotacion.equals("cerrada"));
		Census result = new Census();
		long start_date = Long.parseLong(fecha_inicio);
		long finish_date = Long.parseLong(fecha_fin);

		Date fecha_comienzo = new Date(start_date);
		Date fecha_final = new Date(finish_date);

		Assert.isTrue(fecha_comienzo.before(fecha_final));

		result.setFechaFinVotacion(fecha_final);
		result.setFechaInicioVotacion(fecha_comienzo);

		result.setIdVotacion(idVotacion);
		result.setTituloVotacion(tituloVotacion);
		if (tipoVotacion.equals("abierta")) {
			result.setTipoCenso("abierto");
		} else {
			result.setTipoCenso("cerrado");
		}
		result.setUsername(username);
		HashMap<String, Boolean> vpo = new HashMap<String, Boolean>();
		result.setVoto_por_usuario(vpo);

		return result;
	}

	/**
	 * Metodo que devuelve los censos en los que se puede registrar un usuario.
	 * Para ello, el censo tiene que ser abierto, estar la votacion activa y que
	 * el usuario dado no se encuentre ya registrado
	 * 
	 * @param username
	 */
	public Collection<Census> findCensusesToRegisterByUser(String username) {
		Collection<Census> result = new ArrayList<Census>();
		Collection<Census> openedCensuses = new ArrayList<Census>();
		openedCensuses = censusRepository.findAllOpenedCensuses();

		for (Census c : openedCensuses) {
			if (!c.getVoto_por_usuario().containsKey(username)
					&& votacionActiva(c.getFechaInicioVotacion(), c.getFechaFinVotacion())) {
				result.add(c);
			}
		}

		return result;
	}

	/**
	 *
	 * Metodo utilizado por cabina para actualizar el estado de voto de un
	 * usuario
	 * 
	 * @param idVotacion
	 *            Identificador de la votacion
	 * @param username
	 *            Nombre de usuario
	 * @return boolean
	 */
	public boolean updateUser(int idVotacion, String tipoVotacion, String username) {
		boolean result = false;
		Assert.isTrue(!username.equals(""));
		Census c = findCensusByVote(idVotacion);
		HashMap<String, Boolean> vpo = c.getVoto_por_usuario();

		if (vpo.containsKey(username) && !vpo.get(username)) {

			vpo.remove(username);
			vpo.put(username, true);
			result = true;
		}

		c.setVoto_por_usuario(vpo);
		save(c);

		return result;
	}

	/**
	 *
	 * Metodo para devolver un json para saber si puede borrar o no puede borrar
	 * una votacion
	 * 
	 * @param idVotacion
	 *            Identificador de la votacion
	 * @param username
	 *            Nombre de usuario
	 * @return format json
	 */
	public String canDelete(int idVotacion, String username) {
		Assert.isTrue(!username.equals(""));
		String res = "";

		Census c = findCensusByVote(idVotacion);

		if (c.getVoto_por_usuario().isEmpty()) {
			res = "[{\"result\":\"yes\"}]";
			delete(c.getId(), username);
		} else {
			res = "[{\"result\":\"no\"}]";
		}

		return res;
	}

	/**
	 *
	 * Metodo para devolver un json para saber si un usuario puede votar en una
	 * determinada votacion
	 * 
	 * @param idVotacion
	 *            Identificador de la votacion
	 * @param username
	 *            Nombre de usuario
	 * @return string format json
	 */
	public String canVote(int idVotacion, String username) {
		Assert.isTrue(!username.equals(""));
		String result = "";
		Boolean canVote = false;

		Census census = findCensusByVote(idVotacion);

		if (census.getVoto_por_usuario().containsKey(username) && !census.getVoto_por_usuario().get(username)) {
			canVote = true;
		}

		if (canVote) {
			result = "{\"result\":\"yes\"}";
		} else {
			result = "{\"result\":\"no\"}";
		}

		return result;
	}

	/**
	 * Metodo que devuelve todos los censos de las votaciones en las que un
	 * usuario puede votar
	 * 
	 * @param username
	 *            Nombre de usuario
	 * @return Collection<census>
	 */

	public Collection<Census> findPossibleCensusesByUser(String username) {
		Assert.isTrue(username != "");
		Collection<Census> allCensuses = findAll();
		Collection<Census> result = new ArrayList<Census>();

		for (Census c : allCensuses) {
			// comprobamos si la votacion esta activa
			if (votacionActiva(c.getFechaInicioVotacion(), c.getFechaFinVotacion())) {
				if (c.getVoto_por_usuario().containsKey(username) && !c.getVoto_por_usuario().get(username)) {
					result.add(c);
				}
			}
		}

		return result;
	}

	/**
	 *
	 * Metodo que devuelve todas los censos que ha creado
	 *
	 * @param username
	 *            Nombre de usuario
	 * @return Collection<census>
	 */
	public Collection<Census> findCensusByCreator(String username) {
		Assert.isTrue(!username.equals(""));
		Collection<Census> cs = censusRepository.findCensusByCreator(username);
		return cs;
	}

	/**
	 * Devuelve un determinado censo de un propietario
	 * 
	 * @param censusId
	 * @param username
	 * @return Census
	 */
	public Census findOneByCreator(int censusId, String username) {
		Assert.isTrue(!username.equals(""));
		Census result;
		result = findOne(censusId);
		Assert.isTrue(result != null);
		Assert.isTrue(result.getUsername().equals(username));

		return result;
	}

	/**
	 * 
	 * Añade un usuario con un username determidado a un censo CERRADO
	 *
	 * @param censusId
	 *            Identificador del censo al que añadir el usuario
	 * @param username
	 *            Creador (propietario) del censo
	 * @param username_add
	 *            Nombre de usuario que se va a añadir al censo
	 */
	public void addUserToClosedCensus(int censusId, String username, String username_add) {
		Census census = findOne(censusId);
		Assert.isTrue(census.getTipoCenso().equals("cerrado"));
		Assert.isTrue(votacionActiva(census.getFechaInicioVotacion(), census.getFechaFinVotacion()));
		Assert.isTrue(census.getUsername().equals(username));
		HashMap<String, Boolean> vpo = census.getVoto_por_usuario();
		Assert.isTrue(!vpo.containsKey(username_add));
		vpo.put(username_add, false);
		census.setVoto_por_usuario(vpo);
		save(census);
		// Envio de correo
		String dirEmail;
		// Fecha para controlar cuando se produce un cambio en el censo
		Date currentMoment = new Date();
		Map<String, String> usernamesAndEmails = RESTClient.getMapUSernameAndEmailByJsonAutentication();
		dirEmail = usernamesAndEmails.get(username_add);
		cuerpoEmail = currentMoment.toString() + "-> Se ha incorporado al censo de " + census.getTituloVotacion();
		try {// Se procede al envio del correo con el resultado de la inclusion
				// en el censo
			Gmail.send(cuerpoEmail, dirEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Añade un usuario a un censo ABIERTO (registrarse en un censo abierto)
	 *
	 * @param censusId
	 *            Identificador del censo al que añadir el usuario
	 * @param username_add
	 *            Nombre de usuario que se va a añadir al censo
	 */
	public void addUserToOpenedCensus(int censusId, String username_add) {
		Census census = findOne(censusId);
		Assert.isTrue(census.getTipoCenso().equals("abierto"));
		Assert.isTrue(votacionActiva(census.getFechaInicioVotacion(), census.getFechaFinVotacion()));
		HashMap<String, Boolean> vpo = census.getVoto_por_usuario();
		Assert.isTrue(!vpo.containsKey(username_add));
		vpo.put(username_add, false);
		census.setVoto_por_usuario(vpo);
		save(census);
		// Envio de correo
		String dirEmail;
		Date currentMoment = new Date();// Fecha para controlar cuando se
										// produce un cambio en el censo
		Map<String, String> usernamesAndEmails = RESTClient.getMapUSernameAndEmailByJsonAutentication();
		dirEmail = usernamesAndEmails.get(username_add);
		cuerpoEmail = currentMoment.toString() + "-> Se ha incorporado al censo de " + census.getTituloVotacion();
		try {// Se procede al envio del correo con el resultado de la inclusion
				// en el censo
			Gmail.send(cuerpoEmail, dirEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Elimina un usuario con un username determidado de un censo CERRADO,
	 * cumpliendo la condicion de que el usuario no tenga voto en ese censo
	 *
	 * @param censusId
	 *            Identificador del censo
	 * @param username
	 *            Creador (propietario) del censo
	 * @param username_add
	 *            Nombre de usuario que se va a añadir al censo
	 */
	public void removeUserOfClosedCensus(int censusId, String username, String username_remove) {
		Census census = findOne(censusId);
		Assert.isTrue(census.getTipoCenso().equals("cerrado"));
		Assert.isTrue(votacionActiva(census.getFechaInicioVotacion(), census.getFechaFinVotacion()));
		HashMap<String, Boolean> vpo = census.getVoto_por_usuario();
		Assert.isTrue(census.getUsername().equals(username));
		Assert.isTrue(vpo.containsKey(username_remove) && !vpo.get(username_remove));
		vpo.remove(username_remove);
		census.setVoto_por_usuario(vpo);
		save(census);
		// Envio de correo
		String dirEmail;
		// Fecha para controlar cuando se produce un cambio en el censo
		Date currentMoment = new Date();
		Map<String, String> usernamesAndEmails = RESTClient.getMapUSernameAndEmailByJsonAutentication();
		dirEmail = usernamesAndEmails.get(username_remove);
		cuerpoEmail = currentMoment.toString() + "-> Se ha eliminado del censo de " + census.getTituloVotacion();
		// Se procede al envio del correo con el resultado de la exclusion del
		// usuario del censo
		try {
			Gmail.send(cuerpoEmail, dirEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Persiste un censo Tambien actualizara este para añadir usuarios y cambiar
	 * su valores de voto
	 * 
	 * @param census
	 * @return census
	 */
	public Census save(Census census) {
		Census c = censusRepository.save(census);
		return c;
	}

	/**
	 * 
	 * Borra un censo determinado cumpliendo la condicion que el mapa de votos
	 * por usuarios debe estar vacia
	 * 
	 * @param censusId
	 *            Identificador del censo
	 * @param username
	 */
	public void delete(int censusId, String username) {
		Census c = findOne(censusId);
		// Puedo borrarlo siempre y cuando no haya usuarios registrados
		Assert.isTrue(c.getVoto_por_usuario().isEmpty());
		Assert.isTrue(c.getUsername().equals(username));
		censusRepository.delete(censusId);
	}

	/**
	 * 
	 * Encuentra un censo determidado por su id
	 * 
	 * @param censusId
	 *            Identificador del censo
	 * @return census
	 */
	public Census findOne(int censusId) {
		Census c = censusRepository.findOne(censusId);
		Assert.notNull(c);
		return c;
	}

	/**
	 * 
	 * Metodo que devuelve un json informando sobre un determinado usuario y su
	 * estado en el voto
	 * 
	 * @param idVotacion
	 *            Identificador de la votacion
	 * @param username
	 *            Nombre de usuario a cerca del cual queremos obtener su estado
	 *            de voto
	 * @return String
	 */
	public String createResponseJson(int idVotacion, String username) {
		String response = "";
		Census c = findCensusByVote(idVotacion);
		// formato: idVotacion, username, true/false
		if (c.getVoto_por_usuario().get(username)) {
			response = response + "{\"idVotacion\":" + idVotacion + ",\"username\":\"" + username + "\",\"result\":"
					+ c.getVoto_por_usuario().get(username) + "}";
		} else {
			response = response + "{\"result\":" + "user dont exist}";

		}
		return response;
	}

	/**
	 * 
	 * Encuentra todos los censos del sistema
	 * 
	 * @return Collection<Census>
	 */
	public Collection<Census> findAll() {
		Collection<Census> result;
		result = censusRepository.findAll();
		return result;
	}

	/**
	 *
	 * Metodo para buscar un censo pur su votacion
	 *
	 * @param idVotacion
	 *            Identificador de la votacion sobre la que se va a buscar un
	 *            censo
	 * @return census
	 */
	public Census findCensusByVote(int idVotacion) {
		Census result = censusRepository.findCensusByVote(idVotacion);
		Assert.notNull(result);
		return result;
	}

	/**
	 *
	 * Metodo creado para saber si existe una votacion activa en el rango de
	 * fechas. Una votacion sera activa si su fecha de fin es posterior a la
	 * fecha actual.
	 * 
	 * @param fecha_inicio
	 *            Fecha inicio de la votacion
	 * @param fecha_fin
	 *            Fecha fin de la votacion
	 * @return true si esta activa
	 */
	private boolean votacionActiva(Date fecha_inicio, Date fecha_fin) {
		Boolean res = false;
		Date fecha_actual = new Date();
		Long fecha_actual_long = fecha_actual.getTime();
		Long fecha_fin_long = fecha_fin.getTime();
		if (fecha_fin_long > fecha_actual_long) {
			res = true;
		}

		return res;
	}

}
