package infraestrutura.model;

import infraestrutura.model.infra.Pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Caderneta extends Pojo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(unique=true)
	private Long protocolo;
	
	@Column(unique=true)
	private Long protocoloDevolucaoProfessor;
	
	@Column(unique=true)
	private Long protocoloRetiradaCoordenador;
	
	@Column(unique=true)
	private Long protocoloDevolucaoCoordenador;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Curso.class)
	private Curso curso;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Disciplina.class)
	private Disciplina disciplina;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Professor.class)
	private Professor professor;
	
	@Column(length=50)
	private String turma;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Coordenador.class)
	private Coordenador coordenador;
	
	@Type(type = Pojo.JODA_LOCALDATE_TYPE)
	@Column(nullable = false)
	private LocalDate dataRetiradaProfessor;
	
	@Type(type = Pojo.JODA_LOCALDATE_TYPE)
	@Column(nullable = false)
	private LocalDate dataFinalDisciplina;
	
	@Type(type = Pojo.JODA_LOCALDATE_TYPE)
	@Column
	private LocalDate dataPrevistaDevolucaoProfessor;
	
	@Type(type = Pojo.JODA_LOCALDATE_TYPE)
	@Column
	private LocalDate dataDevolucaoProfessor;
	
	@Type(type = Pojo.JODA_LOCALDATE_TYPE)
	@Column
	private LocalDate dataRetiradaCoordenador;
	
	@Type(type = Pojo.JODA_LOCALDATE_TYPE)
	@Column
	private LocalDate dataPrevistaDevolucaoCoordenador;
	
	@Type(type = Pojo.JODA_LOCALDATE_TYPE)
	@Column
	private LocalDate dataDevolucaoCoordenador;
	
	@Enumerated(EnumType.STRING)
	private SituacaoCaderneta situacao;
	
	public Caderneta(){
		situacao = SituacaoCaderneta.CADERNETA_COM_PROFESSOR;
	}

	public Long getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(Long protocolo) {
		this.protocolo = protocolo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public LocalDate getDataFinalDisciplina() {
		return dataFinalDisciplina;
	}

	public void setDataFinalDisciplina(LocalDate dataFinalDisciplina) {
		this.dataFinalDisciplina = dataFinalDisciplina;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public LocalDate getDataPrevistaDevolucaoProfessor() {
		return dataPrevistaDevolucaoProfessor;
	}
	
	public void setDataPrevistaDevolucaoProfessor(
			LocalDate dataPrevistaDevolucaoProfessor) {
		this.dataPrevistaDevolucaoProfessor = dataPrevistaDevolucaoProfessor;
	}

	public LocalDate getDataDevolucaoProfessor() {
		return dataDevolucaoProfessor;
	}
	
	public void setDataDevolucaoProfessor(LocalDate dataDevolucaoProfessor) {
		this.dataDevolucaoProfessor = dataDevolucaoProfessor;
	}

	public LocalDate getDataPrevistaDevolucaoCoordenador() {
		return dataPrevistaDevolucaoCoordenador;
	}
	
	public void setDataPrevistaDevolucaoCoordenador(
			LocalDate dataPrevistaDevolucaoCoordenador) {
		this.dataPrevistaDevolucaoCoordenador = dataPrevistaDevolucaoCoordenador;
	}

	public LocalDate getDataDevolucaoCoordenador() {
		return dataDevolucaoCoordenador;
	}
	
	public void setDataDevolucaoCoordenador(LocalDate dataDevolucaoCoordenador) {
		this.dataDevolucaoCoordenador = dataDevolucaoCoordenador;
	}
	
	public boolean isCadernetaComProfessor(){
		return SituacaoCaderneta.CADERNETA_COM_PROFESSOR.equals(situacao);
	}
	
	public boolean isCadernetaComCoordenador(){
		return SituacaoCaderneta.CADERNETA_COM_COORDENADOR.equals(situacao);
	}
	
	public boolean isCadernetaConcluida(){
		return SituacaoCaderneta.CONCLUIDO.equals(situacao);
	}

	public Long getProtocoloDevolucaoProfessor() {
		return protocoloDevolucaoProfessor;
	}

	public void setProtocoloDevolucaoProfessor(Long protocoloDevolucaoProfessor) {
		this.protocoloDevolucaoProfessor = protocoloDevolucaoProfessor;
	}

	public SituacaoCaderneta getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoCaderneta situacao) {
		this.situacao = situacao;
	}

	public Long getProtocoloRetiradaCoordenador() {
		return protocoloRetiradaCoordenador;
	}

	public void setProtocoloRetiradaCoordenador(Long protocoloRetiradaCoordenador) {
		this.protocoloRetiradaCoordenador = protocoloRetiradaCoordenador;
	}

	public Long getProtocoloDevolucaoCoordenador() {
		return protocoloDevolucaoCoordenador;
	}

	public void setProtocoloDevolucaoCoordenador(Long protocoloDevolucaoCoordenador) {
		this.protocoloDevolucaoCoordenador = protocoloDevolucaoCoordenador;
	}

	public LocalDate getDataRetiradaCoordenador() {
		return dataRetiradaCoordenador;
	}

	public void setDataRetiradaCoordenador(LocalDate dataRetiradaCoordenador) {
		this.dataRetiradaCoordenador = dataRetiradaCoordenador;
	}

	public LocalDate getDataRetiradaProfessor() {
		return dataRetiradaProfessor;
	}

	public void setDataRetiradaProfessor(LocalDate dataRetiradaProfessor) {
		this.dataRetiradaProfessor = dataRetiradaProfessor;
	}
	
	public boolean isProfessorNoPrazo(){
		if(dataDevolucaoProfessor != null){
			return !dataDevolucaoProfessor.isAfter(dataPrevistaDevolucaoProfessor);
		}else{
			return !(new LocalDate()).isAfter(dataPrevistaDevolucaoProfessor);
		}
	}
	
	public boolean isCoordenadorNoPrazo(){
		if(dataDevolucaoCoordenador!= null){
			return !dataDevolucaoCoordenador.isAfter(dataPrevistaDevolucaoCoordenador);
		}else{
			return !(new LocalDate()).isAfter(dataPrevistaDevolucaoCoordenador);
		}
	}
	
}
