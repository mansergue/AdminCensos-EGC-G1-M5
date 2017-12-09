
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class UserAccountPerCensus extends DomainEntity {

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------

	private Census		census;
	private UserAccount	userAccount;


	@NotNull
	@ManyToOne(optional = false)
	public Census getCensus() {
		return this.census;
	}

	public void setCensus(final Census census) {
		this.census = census;
	}

	@NotNull
	@ManyToOne(optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
