package domain;

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
public class Vote extends DomainEntity {
//	implements Serializable
//	private static final long serialVersionUID = 749544364605664829L;
	private int id_votacion;
	private String titulo;
	private Date fecha_creacion;
	private Date fecha_cierre;
	private String cp;

	public Vote() {
		super();
	}

	@Column(unique = true)
	public int getIdVotacion() {
		return id_votacion;
	}

	public void setIdVotacion(int id_votacion) {
		this.id_votacion = id_votacion;
	}

	@NotBlank
	@Length(min = 5, max = 100, message = "The field must be between 5 and 10 characters")
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFecha_cierre() {
		return fecha_cierre;
	}


	public void setFecha_cierre(Date fecha_cierre) {
		this.fecha_cierre = fecha_cierre;
	}

	public String getCP() {
		return cp;
	}

	public void setCP(String cp) {
		this.cp = cp;
	}
}
