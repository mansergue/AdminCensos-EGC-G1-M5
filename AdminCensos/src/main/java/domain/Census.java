
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Census extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	title;
	private String	postalCode;

	@Enumerated(EnumType.STRING)
	private CA		ca;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}

	@Valid
	@NotNull
	public CA getCa() {
		return this.ca;
	}

	public void setCa(final CA ca) {
		this.ca = ca;
	}


	// Relationships ----------------------------------------------------------

	private Collection<Poll>					polls;
	private Collection<UserAccountPerCensus>	uap;


	@NotNull
	@OneToMany(mappedBy = "census", cascade = CascadeType.ALL)
	public Collection<Poll> getPolls() {
		return this.polls;
	}

	public void setPolls(final Collection<Poll> polls) {
		this.polls = polls;
	}

	@NotNull
	@OneToMany(mappedBy = "census", cascade = CascadeType.ALL)
	public Collection<UserAccountPerCensus> getUap() {
		return this.uap;
	}

	public void setUap(final Collection<UserAccountPerCensus> uap) {
		this.uap = uap;
	}

}
