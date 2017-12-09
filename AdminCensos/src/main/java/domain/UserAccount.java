
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class UserAccount extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	username;
	private String	password;
	private String	email;
	private Role	role;


	@NotNull
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@NotNull
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Email
	@NotBlank
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Valid
	@NotNull
	public Role getRole() {
		return this.role;
	}

	public void setRole(final Role role) {
		this.role = role;
	}


	// Relationships ----------------------------------------------------------

	private Collection<UserAccountPerCensus>	uap;
	private Cookie								cookie;
	private User								user;


	@OneToMany
	@NotNull
	public Collection<UserAccountPerCensus> getUap() {
		return this.uap;
	}

	public void setUap(final Collection<UserAccountPerCensus> uap) {
		this.uap = uap;
	}

	@NotNull
	@OneToOne(optional = true)
	public Cookie getCookie() {
		return this.cookie;
	}

	public void setCookie(final Cookie cookie) {
		this.cookie = cookie;
	}

	@NotNull
	@OneToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
