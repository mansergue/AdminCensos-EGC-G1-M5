package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Vote;


@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
	
	// Buscar un censo de una votación
	
		@Query("select v from Vote v where v.idVotacion = ?1")
		public Vote findVoteByVote(int idVotacion);

		//Busca voto por titulo
		@Query("select v from Vote v where v.titulo = ?1")
		public Vote findVoteByTitle(String titulo);
}