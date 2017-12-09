
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	title;
	private String	description;
	private Boolean	optional;
	private Boolean	multiple;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Boolean getOptional() {
		return this.optional;
	}

	public void setOptional(final Boolean optional) {
		this.optional = optional;
	}

	public Boolean getMultiple() {
		return this.multiple;
	}

	public void setMultiple(final Boolean multiple) {
		this.multiple = multiple;
	}


	// Relationships ----------------------------------------------------------

	private Collection<QuestionOption>	questionOption;
	private Poll						poll;


	@NotNull
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	public Collection<QuestionOption> getQuestionOption() {
		return this.questionOption;
	}

	public void setQuestionOption(final Collection<QuestionOption> questionOption) {
		this.questionOption = questionOption;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Poll getPoll() {
		return this.poll;
	}

	public void setPoll(final Poll poll) {
		this.poll = poll;
	}

}
