package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity{
	
	// Attributes
	private Date moment;
	private String subject;
	private String body;
	
	// Constructor
	public Message(){
		super();
	}
	
	//Getters and Setters 
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Past
	@NotNull
	public Date getMoment(){
		return moment;
	}
	public void setMoment(Date moment){
		this.moment=moment;
	}
	
	@NotBlank
	public String getSubject(){
		return subject;
	}
	public void setSubject(String subject){
		this.subject=subject;
	}
	
	@NotBlank
	public String getBody(){
		return body;
	}
	public void setBody(String body){
		this.body=body;
	}
	
	
	// Relationships
	private Folder folder;
	private User sender;
	private User recipient;

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Folder getFolder(){
		return folder;
	}
	public void setFolder(Folder folder){
		this.folder=folder;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public User getSender(){
		return sender;
	}
	public void setSender(User sender){
		this.sender=sender;
	}
	
//	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public User getRecipient(){
		return recipient;
	}
	public void setRecipient(User recipient){
		this.recipient=recipient;
	}
	

}
