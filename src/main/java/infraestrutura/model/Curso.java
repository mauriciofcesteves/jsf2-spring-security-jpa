package infraestrutura.model;

import infraestrutura.model.infra.Pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hsqldb.lib.StringUtil;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Curso extends Pojo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(length=15, unique=true)
	private String codigo;
	
	@Column(length=50)
	private String nome;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity= Coordenador.class)
	private Coordenador coordenador;
	
	@ManyToMany
	@JoinTable(joinColumns=@JoinColumn(name="curso_id"), inverseJoinColumns=@JoinColumn(name="disciplina_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Disciplina> disciplinas;
	
	public Curso(){
		disciplinas = new ArrayList<Disciplina>();
	}
	
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

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	public boolean ehValido(){
		return !StringUtil.isEmpty(codigo);
	}

}
