package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Vote;

import repositories.VoteRepository;

@Component
@Transactional
public class StringToVoteConverter implements Converter<String, Vote> {

	@Autowired
	VoteRepository voteRepository;

	@Override
	public Vote convert(String text) {
		Vote result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = voteRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}