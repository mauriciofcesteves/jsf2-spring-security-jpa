package infraestrutura.model;

import infraestrutura.model.infra.Pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Configuracao extends Pojo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(length=15, unique=true, nullable=false)
	private String codigo;
	
	@Column(length=50, nullable = false)
	private String nome;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
