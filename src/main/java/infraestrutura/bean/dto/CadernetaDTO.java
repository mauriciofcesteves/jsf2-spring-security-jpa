package infraestrutura.bean.dto;

import java.io.Serializable;

public class CadernetaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String curso;
	private String disciplina;
	private String turma;
	private String coordenador;
	private String professor;
	private String dataPrevistaDevolucao;
	private String situacao;
	private boolean dentroPrazo;
	
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public String getCoordenador() {
		return coordenador;
	}
	public void setCoordenador(String coordenador) {
		this.coordenador = coordenador;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public String getDataPrevistaDevolucao() {
		return dataPrevistaDevolucao;
	}
	public void setDataPrevistaDevolucao(String dataPrevistaDevolucao) {
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public boolean isDentroPrazo() {
		return dentroPrazo;
	}
	public void setDentroPrazo(boolean dentroPrazo) {
		this.dentroPrazo = dentroPrazo;
	}
}
	
