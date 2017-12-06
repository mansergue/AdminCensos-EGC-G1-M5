package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Access(AccessType.PROPERTY)
public class User extends DomainEntity{
	
	private String name;
	private String surname;
	private String email;
	private String genre;
	private String autonomous_community;
	private int age;
	private String role;
	
	@NotBlank
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	@NotBlank
	public String getSurname(){
		return surname;
	}
	public void setSurname(String surname){
		this.surname=surname;
	}
        
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAutonomous_community() {
		return autonomous_community;
	}

	public void setAutonomous_community(String autonomous_community) {
		this.autonomous_community = autonomous_community;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String getRole(){
		return role;
	}
	public void setRole(String role){
		this.role=role;
	}
	
	
	//Realationships
		UserAccount userAccount;
		private Collection<Folder> folders;
		private Collection<Message> sentMessages;
		private Collection<Message> receivedMessages;
		
		@NotNull
		@OneToOne(cascade = CascadeType.ALL, optional = false)
		public UserAccount getUserAccount() {
			return userAccount;
		}

		public void setUserAccount(UserAccount userAccount) {
			this.userAccount = userAccount;
		}

		@Valid
		@OneToMany (mappedBy="user")
		public Collection<Folder> getFolders(){
			return folders;
		}
		public void setFolders(Collection<Folder> folders){
			this.folders=folders;
		}
		public void addFolder(Folder folder){
			folders.add(folder);
		}
		public void removeFolder(Folder folder){
			folders.remove(folder);
		}
		

		@Valid
		@OneToMany (mappedBy="sender")
		public Collection<Message> getSentMessages(){
			return sentMessages;
		}
		public void setSentMessages(Collection<Message> sentMessages){
			this.sentMessages=sentMessages;
		}
		public void addSentMessage(Message message){
			sentMessages.add(message);
		}
		public void removeSentMessage(Message message){
			sentMessages.remove(message);
		}
		
		
		@Valid
		@OneToMany (mappedBy="recipient")
		public Collection<Message> getReceivedMessages(){
			return receivedMessages;
		}
		public void setReceivedMessages(Collection<Message> receivedMessages){
			this.receivedMessages=receivedMessages;
		}
		public void addReceivedMessage(Message message){
			receivedMessages.add(message);
		}
		public void removeReceivedMessage(Message message){
			receivedMessages.remove(message);
		}
		
}
