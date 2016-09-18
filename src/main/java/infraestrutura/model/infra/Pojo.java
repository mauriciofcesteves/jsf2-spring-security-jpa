package infraestrutura.model.infra;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Pojo implements Serializable{

	private static final long serialVersionUID = 1L;

	/** Tipo DATE da biblioteca JodaTime. */
	public static final String JODA_LOCALDATE_TYPE = "org.joda.time.contrib.hibernate.PersistentLocalDate";

	/** Tipo TIME da biblioteca JodaTime. */
	public static final String JODA_LOCALTIME_TYPE = "org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime";

	/** Tipo DATETIME da biblioteca JodaTime. */
	public static final String JODA_DATETIME_TYPE = "org.joda.time.contrib.hibernate.PersistentDateTime";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(columnDefinition="BOOLEAN")
	private Boolean ativo;
	
	public Pojo(){
		this.ativo = true;
	}
	
	@Override
	public int hashCode() {
		if(getId()==null){
			return 0;
		}
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pojo other = (Pojo) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
