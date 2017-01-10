
package services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import domain.Vote;
import repositories.VoteRepository;

@Service
@Transactional
public class VoteService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private VoteRepository voteRepository;

	// Constructors -----------------------------------------------------------

	public VoteService() {
		super();
	}

	// Methods ----------------------------------------------------------------
	public Vote create(int idVotacion, String titulo, String fechaCreacion, String fechaCierre, String cp) throws ParseException{
		Vote result=new Vote();
		
		result.setIdVotacion(idVotacion);
		result.setTitulo(titulo);
		
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaCreacionConvertida=null;
		Date fechaCierreConvertida=null;
		try {
			fechaCreacionConvertida = formatoDelTexto.parse(fechaCreacion);
			fechaCierreConvertida = formatoDelTexto.parse(fechaCierre);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		result.setFechaCreacion(fechaCreacionConvertida);
		result.setFechaCierre(fechaCierreConvertida);
		
		result.setCp(cp);
		
		return result;
		
	}
	
	public Vote findOne(int voteId) {
		Vote c = voteRepository.findOne(voteId);
		Assert.notNull(c);
		return c;
	}

	public Collection<Vote> findAll() {
		Collection<Vote> result=new ArrayList<Vote>();
		result=voteRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Vote save(Vote vote) {
		Assert.notNull(vote);
		Vote c = voteRepository.save(vote);
		return c;
	}
	
	public Vote findVoteByVote(int idVotacion){
		Vote vote=voteRepository.findVoteByVote(idVotacion);
		Assert.notNull(vote);
		return vote;
	}
	
	public Vote findVoteByVote(int idVotacion){
		Vote vote=voteRepository.findVoteByVote(idVotacion);
		return vote;
	}
	
	public void delete(int idVotacion) {
		Vote vote = findVoteByVote(idVotacion);

		voteRepository.delete(vote);
	}
	
	//Método que mete en nuestra base de datos todas las votaciones del grupo de recuento
	public void popularVotaciones() {
		// Leer la siguiente uri y mapearla en la clase Vote:
		// https://recuento.agoraus1.egc.duckdns.org/api/verVotaciones
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("https://recuento.agoraus1.egc.duckdns.org/api/verVotaciones",
				String.class);
		System.out.println(result);
		String aux = result.replace("{\"estado\":200,\"votaciones\":", "");
		System.out.println(aux);
		String[] lista = aux.split("},");
		String[] lista2 = aux.split(",");
		Vote vote=new Vote();
		
		for (@SuppressWarnings("unused")String voto : lista) {
			for (String field : lista2) {
				
				System.out.println("field: "+field);
				if (field.contains("id_votacion")) {
					String[] auxList = field.split(":");
					String id_votacion = auxList[1];
					String[] id_list = id_votacion.split("}");
					String finalId = id_list[0];
					int idConverted = Integer.parseInt(finalId);
					
					vote.setIdVotacion(idConverted);
					vote.setId(idConverted);
				}
				if (field.contains("titulo")) {
					String[] auxList = field.split(":");
					String titulo = auxList[1];
					titulo = titulo.replaceAll("\"", "");
					System.out.println("titulo: "+titulo);
					vote.setTitulo(titulo);
				}
				if (field.contains("fecha_creacion")) {
					String[] auxList = field.split(":");
					String fecha_creacion = auxList[1];
					fecha_creacion = fecha_creacion.replaceAll("\"", "");
					SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
					Date fechaConvertida=null;
					try {
						fechaConvertida = formatoDelTexto.parse(fecha_creacion);
					} catch (ParseException e) {
						e.printStackTrace();
					}
	
					vote.setFechaCreacion(fechaConvertida);
				}
				if (field.contains("fecha_cierre")) {
					String[] auxList = field.split(":");
					String fecha_cierre = auxList[1];
					fecha_cierre = fecha_cierre.replaceAll("\"", "");
					SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
					Date fechaConvertida=null;
					try {
						fechaConvertida = formatoDelTexto.parse(fecha_cierre);
					} catch (ParseException e) {
						e.printStackTrace();
					}
	
					vote.setFechaCierre(fechaConvertida);
				}
				if (field.contains("cp")) {
					String[] auxList = field.split(":");
					String cp = auxList[1];
					cp = cp.replaceAll("\"", "");
					cp=cp.replaceAll("}", "");
					cp=cp.replaceAll("]", "");
					vote.setCp(cp);
				}
				Collection<Vote> votes=new ArrayList<Vote>();
				Collection<Integer> ids=new ArrayList<Integer>();
				try{
					votes.addAll(findAll());
					for(Vote v:votes){
						ids.add(v.getIdVotacion());
					}
				}
				catch(Exception e){
					System.out.println(e);
				}
				System.out.println(ids.isEmpty());
				if(!ids.contains(vote.getIdVotacion())||ids.isEmpty()==true){
					System.out.println("entra");
					save(vote);
					System.out.println("y lo pasa");
				}
//				save(vote);
			}
			
			System.out.println(vote.getIdVotacion()+vote.getTitulo()+vote.getFechaCreacion()+vote.getFechaCierre()+vote.getCp());
		}
		System.out.println("y llega al final");
	}
	
	
}

