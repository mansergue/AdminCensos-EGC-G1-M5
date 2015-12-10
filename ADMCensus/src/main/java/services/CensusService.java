package services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CensusRepository;
import domain.Census;

@Service
@Transactional
public class CensusService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CensusRepository censusRepository;

	// Constructors -----------------------------------------------------------

	public CensusService() {
		super();
	}

	// Others Methods----------------------------------------------------------

	/**
	 * Crea un censo a partir de una votación
	 * 
	 * @param idVotacion
	 *            = Identificador de la votación
	 * @param username
	 *            = Nombre de usuario que ha creado la votacion
	 * @param fecha_inicio
	 *            = Fecha de inicio de la votacion
	 * @param fecha_fin
	 *            = Fecha de fin de la votacion
	 * @param tituloVotacion
	 *            = Cadena de texto con el título de la votaciçon
	 * @return census
	 * @throws ParseException
	 */
	public Census create(int idVotacion, String username, String fecha_inicio, String fecha_fin, String tituloVotacion)
			throws ParseException {
		Assert.isTrue(!username.equals(""));
		Census c = new Census();
		long start_date = Long.parseLong(fecha_inicio);
		long finish_date = Long.parseLong(fecha_fin);

		Date fecha_comienzo = new Date(start_date);
		Date fecha_final = new Date(finish_date);
		Assert.isTrue(fecha_comienzo.before(fecha_final));

		c.setFechaFinVotacion(fecha_final);
		c.setFechaInicioVotacion(fecha_comienzo);
		c.setIdVotacion(idVotacion);
		c.setTituloVotacion(tituloVotacion);
		c.setUsername(username);
		HashMap<String, Boolean> vpo = new HashMap<String, Boolean>();
		c.setVoto_por_usuario(vpo);
		return c;
	}

	public Collection<Census> findAll(int censusID) {
		Collection<Census> result;
		result = censusRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	/**
	 * Método usado por cabina que actualiza a true el estado de voto de un user
	 * 
	 * @param idVotacion
	 *            = Identificador de la votación
	 * @param username
	 *            = Nombre de usuario
	 * @return boolean
	 */
	public boolean updateUser(int idVotacion, String username) {
		boolean res = false;
		Assert.hasLength(username);
		Census c = findCensusByVote(idVotacion);
		HashMap<String, Boolean> vpo = c.getVoto_por_usuario();

		if (vpo.containsKey(username) && !vpo.get(username)) {
			vpo.remove(username);
			vpo.put(username, true);
			res = true;
		}

		c.setVoto_por_usuario(vpo);
		save(c);
		return res;
	}

	/**
	 * Devuelve un json para saber si se puede borrar o no una votación
	 * 
	 * @param idVotacion
	 *            = Identificador de la votación
	 * @param username
	 *            = Nombre de usuario
	 * @return format json
	 */
	public String canDelete(int idVotacion, String username) {
		Assert.hasLength(username);
		String res = "";
		Census c = findCensusByVote(idVotacion);

		if (c.getVoto_por_usuario().isEmpty()) {
			res = "[{\"result\":\"yes\"}]";// Si se puede se elimina
			delete(c.getId(), username);
		} else {
			res = "[{\"result\":\"no\"}]";
		}
		return res;
	}

	/**
	 * Devuelve un json indicando si un usuario puede votar en una determinada
	 * votación
	 * 
	 * @param idVotacion
	 *            = Identificador de la votación
	 * @param username
	 *            = Nombre de usuario
	 * @return string format json
	 */
	public String canVote(int idVotacion, String username) {
		Assert.hasLength(username);
		String res = "";
		Census c = findCensusByVote(idVotacion);
		Assert.notNull(c);

		if (c.getVoto_por_usuario().containsKey(username) && !c.getVoto_por_usuario().get(username)) {
			res = "{\"result\":\"yes\"}";
		} else {
			res = "{\"result\":\"no\"}";
		}
		return res;
	}

	/**
	 * Devuelve los censos de votaciones activas en las que un user aún no ha
	 * votado
	 * 
	 * @param username
	 *            = Nombre de usuario
	 * @return Collection<census>
	 */
	public Collection<Census> findCensusByUser(String username) {
		Assert.hasLength(username);
		Collection<Census> result = new ArrayList<Census>();
		Collection<Census> aux = findAll();

		for (Census c : aux) {
			HashMap<String, Boolean> vpo = c.getVoto_por_usuario();
			if (vpo.containsKey(username)) {
				boolean votado = vpo.get(username);
				boolean activa = votacionActiva(c.getFechaInicioVotacion(), c.getFechaFinVotacion());
				if (!votado && activa) {
					result.add(c);
				}
			}
		}
		return result;
	}

	/**
	 * Devuelve todos los censos que de un propietario
	 * 
	 * @param username
	 *            = Nombre de usuario
	 * @return Collection<census>
	 */
	public Collection<Census> findCensusByCreator(String username) {
		Assert.hasLength(username);
		Collection<Census> result = censusRepository.findCensusByCreator(username);
		return result;
	}

	/**
	 * Añade un usuario a un censo
	 * 
	 * @param censusId
	 *            = Identificador del censo al que añadir el usuario
	 * @param username
	 *            = Creador (propietario) del censo
	 * @param username_add
	 *            = Usuario que se va a añadir al censo
	 */
	public void addUserToCensus(int censusId, String username, String username_add) {
		Census c = findOne(censusId);
		Date fechaActual = new Date();
		Assert.isTrue(c.getFechaFinVotacion().after(fechaActual));
		Assert.isTrue(c.getUsername().equals(username));
		HashMap<String, Boolean> vpo = c.getVoto_por_usuario();
		Assert.isTrue(!vpo.containsKey(username_add));
		vpo.put(username_add, false);
		c.setVoto_por_usuario(vpo);
		save(c);
	}

	/**
	 * Elimina un usuario de un censo si no ha votado aún
	 * 
	 * @param censusId
	 *            = Identificador del censo
	 * @param username
	 *            = Creador (propietario) del censo
	 * @param username_add
	 *            = Usuario que se va a eliminar del censo
	 */
	public void removeUserToCensus(int censusId, String username, String username_remove) {
		Census c = findOne(censusId);
		Date fechaActual = new Date();
		Assert.isTrue(c.getFechaFinVotacion().after(fechaActual));
		HashMap<String, Boolean> vpo = c.getVoto_por_usuario();
		Assert.isTrue(c.getUsername().equals(username));
		Assert.isTrue(vpo.containsKey(username_remove) && !vpo.get(username_remove));
		vpo.remove(username_remove);
		c.setVoto_por_usuario(vpo);
		save(c);
	}

	/**
	 * Guardar un censo
	 * 
	 * @param census
	 * @return census
	 */
	public Census save(Census census) {
		Census c = censusRepository.save(census);
		return c;
	}

	/**
	 * ELimina un censo si no tiene usuarios
	 * 
	 * @param censusId
	 *            = Identificador del censo
	 * @param username
	 */
	public void delete(int censusId, String username) {
		Census c = findOne(censusId);
		Assert.isTrue(c.getVoto_por_usuario().isEmpty());
		Assert.isTrue(c.getUsername().equals(username));
		censusRepository.delete(censusId);
	}

	/**
	 * Encuentra un censo dado su id
	 * 
	 * @param censusId
	 *            = Identificador del censo
	 * @return census
	 */
	public Census findOne(int censusId) {
		Census c = censusRepository.findOne(censusId);
		Assert.notNull(c);
		return c;
	}

	/**
	 * Devuelve el censo de una votación
	 * 
	 * @param idVotacion
	 *            = Identificador de la votacion
	 * @return census
	 */
	public Census findOneByVote(int idVotacion) {
		Census c = censusRepository.findCensusByVote(idVotacion);
		Assert.notNull(c);
		return c;
	}

	/**
	 * Devuelve un json informando sobre un usuario y su estado en el voto
	 * 
	 * @param idVotacion
	 *            = Identificador de la votacion
	 * @param username
	 *            = Usuario del cual queremos obtener su estado de voto
	 * @return String
	 */
	public String createResponseJson(int idVotacion, String username) {
		String response = "";
		Census c = findOneByVote(idVotacion);
		// formato: idVotacion, username, true/false
		if (c.getVoto_por_usuario().get(username)) {
			response = response + "{\"idVotacion\":" + idVotacion + ",\"username\":\"" + username + "\",\"result\":"
					+ c.getVoto_por_usuario().get(username) + "}";
		} else {
			response = response + "{\"result\":" + "0}";
		}
		return response;
	}

	/**
	 * Encuentra todos los censos del sistema
	 * 
	 * @return Collection<Census>
	 */
	public Collection<Census> findAll() {
		return censusRepository.findAll();
	}

	/**
	 * Metodo para buscar el censo de una votación
	 * 
	 * @param idVotacion
	 *            = Id de la votacion sobre la que se busca un censo
	 * @return census
	 */
	public Census findCensusByVote(int idVotacion) {
		Census c = censusRepository.findCensusByVote(idVotacion);
		Assert.notNull(c);
		return c;
	}

	/**
	 * Metodo creado para saber si existe una votacion activa
	 * 
	 * @param fecha_inicio
	 *            = Fecha inicio de la votacion
	 * @param fecha_fin
	 *            = Fecha fin de la votacion
	 * @return true si está activa
	 */
	private boolean votacionActiva(Date fecha_inicio, Date fecha_fin) {
		Boolean res = false;
		Long fecha_actual = new Date().getTime();
		Long fecha_inicio_long = fecha_inicio.getTime();
		Long fecha_fin_long = fecha_fin.getTime();
		if (fecha_inicio_long < fecha_actual && fecha_fin_long > fecha_actual) {
			res = true;
		}
		return res;
	}

	// NUEVA FUNCIONALIDAD 2015/2016

	/**
	 * Metodo para filtrar usuarios de un censo
	 * 
	 * @param username
	 *            = Nombre del admin del censo
	 * @param userSearch
	 *            = Usuario que buscamos
	 * @param censusId
	 * @return el filtro de búsqueda
	 */
	public Collection<Census> findUser(String username, String userSearch, int censusId) {
		Assert.hasLength(username);
		Assert.hasLength(userSearch);
		Assert.isTrue(censusId > 0);
		Census c = findOne(censusId);
		Assert.isTrue(c.getUsername().equals(username));
		Collection<Census> res = new ArrayList<Census>();

		HashMap<String, Boolean> vpo = c.getVoto_por_usuario();
		for (String s : vpo.keySet()) {
			if (s.equals(userSearch)) {
				res.add(c);
			}
		}
		return res;
	}

	/**
	 * Metodo para buscar un usuario en concreto para un censo determinado
	 * 
	 * @param userSearch
	 *            = Usuario que buscamos
	 * @param censusId
	 *            = Id del censo sobre el que vamos a realizar el filtro
	 * @return el filtro de búsqueda
	 */

	public Collection<String> findByUsername(String username, int censusId) {
		Assert.hasLength(username);
		Assert.isTrue(censusId > 0);
		Census censo = findOne(censusId);
		Assert.notNull(censo);
		// Está será la coleccion en la que devolveremos aquellos votantes del
		// census
		// que tengan como username el que se pasa como parámetro en la función
		Collection<String> result = new ArrayList<String>();

		HashMap<String, Boolean> vpo = censo.getVoto_por_usuario();
		// Comprobamos que de todos los votantes del censo haya alguno que tenga
		// como
		// username el pasado por parámetros
		for (String votante : vpo.keySet()) {
			System.out.println(votante);
			if (votante.equals(username)) {
				// Añadimos los votantes que pasan el filtro
				result.add(votante);
			}
		}

		return result;
	}

}
