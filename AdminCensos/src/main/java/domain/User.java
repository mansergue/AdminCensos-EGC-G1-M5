
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class User extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	name;
	private String	surname;
	private Genre	genre;
	private Date	birthDate;
	private String	dni;
	private CA		ca;


	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@NotNull
	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(final Genre genre) {
		this.genre = genre;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(final String dni) {
		this.dni = dni;
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

	private UserAccount	userAccount;


	@OneToOne(optional = false)
	@NotNull
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
