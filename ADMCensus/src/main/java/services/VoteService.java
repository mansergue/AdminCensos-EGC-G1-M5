package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

	public Vote findOne(int voteId) {
		Vote c = voteRepository.findOne(voteId);
		Assert.notNull(c);
		return c;
	}

	public Collection<Vote> findAll() {
		Collection<Vote> result;
		result = voteRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Vote save(Vote vote) {
		Vote c = voteRepository.save(vote);
		return c;
	}

}
