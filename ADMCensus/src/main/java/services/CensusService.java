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

	public Collection<Census> findAll(int censusID) {
		Collection<Census> result;

		result = censusRepository.findAll();
		Assert.notNull(result);

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
	public boolean updateUser(int idVotacion, String username) {
		boolean res = false;
		Assert.isTrue(!username.equals(""));
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

		if (census != null) {
			// si el censo es abierto y no esta registrado su nombre, puede
			// votar (si ya esta registrado es porque ya ha votado)
			if (census.getTipoCenso().equals("abierto") && !census.getVoto_por_usuario().containsKey(username)) {
				canVote = true;
			} else if (census.getVoto_por_usuario().containsKey(username)
					&& !census.getVoto_por_usuario().get(username)) {
				canVote = true;
			}
		}

		if (canVote) {
			result = "{\"result\":\"yes\"}";
		} else {
			result = "{\"result\":\"no\"}";
		}

		return result;
	}

	/**
	 * DEPRECATED Metodo que devuelve todas las votaciones ACTIVAS en las que un
	 * usuario está censado y aun no ha votado (votaciones cerradas en las que
	 * un usuario puede votar)
	 *
	 * @param username
	 *            Nombre de usuario
	 * @return Collection<census>
	 */
	// public Collection<Census> findCensusByUser(String username) {
	// Assert.isTrue(!username.equals(""));
	// Collection<Census> cs = new ArrayList<Census>();
	// Collection<Census> allCensuses = findAll(); // Obtengo todos los censos
	//
	// for (Census c : allCensuses) {
	// HashMap<String, Boolean> vpo = c.getVoto_por_usuario();
	// if (vpo.containsKey(username)) {
	// boolean votado = vpo.get(username);
	// boolean activa = votacionActiva(c.getFechaInicioVotacion(),
	// c.getFechaFinVotacion());
	// // Si el usuario esta en el censo, la votación esta activa y no
	// // ha votado tengo que mostrar su censo.
	// if (!votado && activa) {
	// cs.add(c);
	// }
	// }
	//
	// }
	//
	// return cs;
	// }

	/**
	 * Metodo que devuelve todos los censos de las votaciones en las que un
	 * usuario puede votar
	 * 
	 * Esto implica tanto las votaciones abiertas y activas, como las cerradas y
	 * activas en las que el usuario este censado, SIEMPRE Y CUANDO EL USUARIO
	 * NO HAYA VOTADO YA
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
				if (c.getTipoCenso().equals("abierto") && !c.getVoto_por_usuario().containsKey(username)) {
					result.add(c);
				} else if (c.getTipoCenso().equals("cerrado") && c.getVoto_por_usuario().containsKey(username)
						&& !c.getVoto_por_usuario().get(username)) {
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
	 * fechas
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
		Long fecha_inicio_long = fecha_inicio.getTime();
		Long fecha_fin_long = fecha_fin.getTime();
		if (fecha_inicio_long < fecha_actual_long && fecha_fin_long > fecha_actual_long) {
			res = true;
		}

		return res;
	}

}
