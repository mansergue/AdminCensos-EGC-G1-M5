
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class OptionPerVote extends DomainEntity {

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------

	private Vote			vote;
	private QuestionOption	questionOption;


	@NotNull
	@ManyToOne(optional = false)
	public Vote getVote() {
		return this.vote;
	}

	public void setVote(final Vote vote) {
		this.vote = vote;
	}

	@NotNull
	@ManyToOne(optional = false)
	public QuestionOption getQuestionOption() {
		return this.questionOption;
	}

	public void setQuestionOption(final QuestionOption questionOption) {
		this.questionOption = questionOption;
	}

}
