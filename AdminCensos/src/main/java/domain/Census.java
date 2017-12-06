package domain;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@XmlRootElement(name = "census")
@Access(AccessType.PROPERTY)
public class Census extends DomainEntity {

	//Nos lo pasa recuento
	// Id que identifica de forma únia a la votación
	private int idVotacion;
	
	// Título de la votación
	private String title;
	
	// Fecha en la que se inicia la votacion
	private Date startDate;
		
	// Fecha en la que finaliza la votación
	private Date endDate;
	
	// Indica si el censo es "abierto" o "cerrado"
	private String tipo;
	
	//Indica el código postal de la votación
	private String postalCode;
	
	//Indica la comunidad de la votación
	private String comunidadAutonoma;
	
	// Username del usuario que crea la votación
	private String usernameCreator;

	//Lo trabajamos nosotros
	// Mapa encargado de asignar un true o false (ha votado o no) a un token  único de un usuario
	private HashMap<String, Boolean> votoPorUsuario = new HashMap<String, Boolean>();
	

	//Mirar qué hacer con census y con questions
	
	public Census() {
		
	}
	
	@Column(unique = true)
	public int getIdVotacion() {
		return idVotacion;
	}
	

	public void setIdVotacion(int idVotacion) {
		this.idVotacion = idVotacion;
	}
	

	@NotBlank
	public String getTitle() {
		return title;
	}
	

	public void setTitle(String title) {
		this.title = title;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}
	

	public void setStartDate(Date startDate) {
		this.startDate=startDate;
	}
	

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate=endDate;
	}
	
	@NotBlank
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getUsernameCreator() {
		return usernameCreator;
	}
	

	public void setUsernameCreator(String usernameCreator) {
		this.usernameCreator = usernameCreator;
	}
	
	
	@MapKeyColumn(name = "token")
	@Column(name = "valor", columnDefinition = "LONGBLOB")
	@CollectionTable(name = "value", joinColumns = @JoinColumn(name = "token") )
	public HashMap<String, Boolean> getVotoPorUsuario() {
		return votoPorUsuario;
	}
	
	public void setVotoPorUsuario(HashMap<String, Boolean> votoPorUsuario) {
		this.votoPorUsuario = votoPorUsuario;
	}

	@NotBlank
	public String getComunidadAutonoma() {
		return comunidadAutonoma;
	}

	public void setComunidadAutonoma(String comunidadAutonoma) {
		this.comunidadAutonoma = comunidadAutonoma;
	}

}
