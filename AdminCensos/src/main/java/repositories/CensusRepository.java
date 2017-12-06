package repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Census;

@Repository
public interface CensusRepository extends JpaRepository<Census, Integer> {
	
	// Buscar un censo de una votación
	
	@Query("select c from Census c where c.idVotacion = ?1")
	public Census findCensusByVote(int idVotacion);
	
	// Buscar un censo por creador
	
	@Query("select c from Census c where c.usernameCreator = ?1")
	public Collection<Census> findCensusByCreator(String usernameCreator);
	
	// Devuelve los censos abiertos
	
	@Query("select c from Census c where c.tipo = 'abierto'")
	public Collection<Census> findAllOpenedCensuses();
	
	//Número de censos abiertos
	@Query("select count(c) from Census c where c.tipo = 'abierto'")
	public int openCensuses();
}
