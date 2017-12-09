
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Cookie extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	number_id;


	@NotBlank
	public String getNumber_id() {
		return this.number_id;
	}

	public void setNumber_id(final String number_id) {
		this.number_id = number_id;
	}


	// Relationships ----------------------------------------------------------

	private UserAccount	userAccount;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
