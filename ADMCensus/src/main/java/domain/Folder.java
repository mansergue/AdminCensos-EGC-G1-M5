package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Folder extends DomainEntity{
	
	// Attributes
	private String name;
	
	// Constructor
	public Folder(){
		super();
	}
	
	//Getters and Setters 
	@NotBlank
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	// Relationships
	private User user;
	private Collection<Message> messages;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public User getUser(){
		return user;
	}
	public void setUser(User user){
		this.user=user;
	}
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="folder")
	public Collection<Message> getMessages(){
		return messages;
	}
	public void setMessages(Collection<Message> messages){
		this.messages=messages;
	}

}

