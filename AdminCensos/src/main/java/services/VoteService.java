
package services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
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
	
	public void delete(int idVotacion) {
		Vote vote = findVoteByVote(idVotacion);

		voteRepository.delete(vote);
	}
	
	//Método que mete en nuestra base de datos todas las votaciones del grupo de recuento
	public void popularVotaciones() throws ParseException{
		RestTemplate restTemplate = new RestTemplate();
		String JSON = restTemplate.getForObject("https://recuento.agoraus1.egc.duckdns.org/api/verVotaciones",String.class);

		JSONObject jsonObject = new JSONObject(JSON);
		JSONArray votaciones = jsonObject.getJSONArray("votaciones");
		Collection<Vote> nuestrosVotos=voteRepository.findAll();
		for (int i = 0; i < votaciones.length(); i++){
		    int idVotacion=votaciones.getJSONObject(i).getInt("id_votacion");
		    String titulo=votaciones.getJSONObject(i).getString("titulo");
		    String fechaCreacion=votaciones.getJSONObject(i).getString("fecha_creacion");
		    String fechaCierre=votaciones.getJSONObject(i).getString("fecha_cierre");
		    String cp=votaciones.getJSONObject(i).getString("cp");
		    
		    Vote vote=create(idVotacion,titulo,fechaCreacion,fechaCierre,cp);
		    if(nuestrosVotos.isEmpty()){//añade el primero
		    	save(vote);
		    }
			else {
				if(findVoteByVote(vote.getIdVotacion())!=null){//si existe lo borra
					System.out.println("entró");
					delete(vote.getIdVotacion());
					System.out.println(findAll());
					save(vote);
				}
				else if(findVoteByVote(vote.getIdVotacion())==null){//si no existe lo añade
					save(vote);
				}
			}
		}
	}
	
	public Vote findVoteByTitle(String titulo){
		return voteRepository.findVoteByTitle(titulo);
	}
	
}

