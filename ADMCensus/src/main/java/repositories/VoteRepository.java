package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer>{

}
