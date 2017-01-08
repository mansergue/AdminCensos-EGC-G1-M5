package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Access(AccessType.PROPERTY)
public class Vote extends DomainEntity implements Serializable{

	// Puede que también haga falta meter la clase de dominio Question
	private static final long serialVersionUID = 749544364605664829L;
	private int idVotacion;
	private String titulo;
	private Date fechaCreacion;
	private Date fechaCierre;
	private String cp;

	public Vote() {
		super();
	}

	@Column(unique = true)
	public int getIdVotacion() {
		return idVotacion;
	}

	public void setIdVotacion(int idVotacion) {
		this.idVotacion = idVotacion;
	}

//	@NotBlank
//	@Length(min = 5, max = 100, message = "The field must be between 5 and 10 characters")
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFechaCierre() {
		return fechaCierre;
	}


	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}
}