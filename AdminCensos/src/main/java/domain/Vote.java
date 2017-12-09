
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Vote extends DomainEntity {

	// Attributes -------------------------------------------------------------

	@Enumerated(EnumType.STRING)
	private VoteType	voteType;

	private Date		voteDate;
	private String		token;


	@Valid
	@NotNull
	public VoteType getVoteType() {
		return this.voteType;
	}

	public void setVoteType(final VoteType voteType) {
		this.voteType = voteType;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	public Date getVoteDate() {
		return this.voteDate;
	}

	public void setVoteDate(final Date voteDate) {
		this.voteDate = voteDate;
	}

	@NotBlank
	public String getToken() {
		return this.token;
	}

	public void setToken(final String token) {
		this.token = token;
	}


	// Relationships ----------------------------------------------------------

	private OptionPerVote	optionVote;


	@Valid
	@OneToMany
	public OptionPerVote getOptionVote() {
		return this.optionVote;
	}

	public void setOptionVote(final OptionPerVote optionVote) {
		this.optionVote = optionVote;
	}

}
