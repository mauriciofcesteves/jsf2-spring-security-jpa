
package infraestrutura.model;

import infraestrutura.model.infra.Pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Professor extends Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(length=11)
	private String cpf;
	
	@Column(length=14)
	private String cnpj;
	
	@Column(length=7)
	private String placa;
	
	@Column(length=50)
	private String seguradora;

	@Column(length=50)
	private String titulacao;
	
	@Type(type = Pojo.JODA_LOCALDATE_TYPE)
	@Column(nullable = true)
	private LocalDate vigenciaInicio;
	
	@Type(type = Pojo.JODA_LOCALDATE_TYPE)
	@Column(nullable = true)
	private LocalDate vigenciaFim;

	@Column(length=100)
	private String observacao;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(String seguradora) {
		this.seguradora = seguradora;
	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public LocalDate getVigenciaInicio() {
		return vigenciaInicio;
	}

	public void setVigenciaInicio(LocalDate vigenciaInicio) {
		this.vigenciaInicio = vigenciaInicio;
	}

	public LocalDate getVigenciaFim() {
		return vigenciaFim;
	}

	public void setVigenciaFim(LocalDate vigenciaFim) {
		this.vigenciaFim = vigenciaFim;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}
