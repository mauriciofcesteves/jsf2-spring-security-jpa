package infraestrutura.model;

import infraestrutura.model.infra.Pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Job extends Pojo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(length=50)
	private String titulo;
	
	@Lob
	private String descricao;

	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Coordenador.class)
	private Coordenador coordenador;
	
	@Type(type = Pojo.JODA_LOCALDATE_TYPE)
	@Column
	private LocalDate dataCadastro;
	
	@Type(type = Pojo.JODA_LOCALDATE_TYPE)
	@Column
	private LocalDate dataPrevista;
	
	@Type(type = Pojo.JODA_LOCALDATE_TYPE)
	@Column
	private LocalDate dataConclusao;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(LocalDate dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public LocalDate getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDate dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	
	public boolean isPermitidoAlterar() {
		return dataConclusao == null;
	}
}
