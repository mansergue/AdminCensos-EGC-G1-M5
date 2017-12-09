
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class QuestionOption extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	description;


	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}


	// Relationships ----------------------------------------------------------

	private OptionPerVote	optionPerVote;
	private Question		question;


	@NotNull
	@OneToMany
	public OptionPerVote getOptionPerVote() {
		return this.optionPerVote;
	}

	public void setOptionPerVote(final OptionPerVote optionPerVote) {
		this.optionPerVote = optionPerVote;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(final Question question) {
		this.question = question;
	}

}
