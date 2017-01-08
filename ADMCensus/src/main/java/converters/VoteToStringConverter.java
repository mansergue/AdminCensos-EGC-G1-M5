package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Vote;

@Component
@Transactional
public class VoteToStringConverter implements Converter<Vote, String> {

	@Override
	public String convert(Vote vote) {
		String result;

		if (vote == null)
			result = null;
		else
			result = String.valueOf(vote.getId());

		return result;
	}

}