package infraestrutura.model;

import infraestrutura.util.Criptografia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Usuario implements Serializable {	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length=30, nullable = false)
	private String login;
	
	@Column(length=50)
	private String nome;
	
	@Column(length=100, nullable = false)
	private String senha;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(joinColumns=@JoinColumn(name="login_usuario"), inverseJoinColumns=@JoinColumn(name="nome_perfil"))
	@Cascade(CascadeType.SAVE_UPDATE)
	List<Perfil> perfis;
	
	@Column(columnDefinition="BOOLEAN")
	private boolean ativado;
	
	public Usuario(){
		this.perfis = new ArrayList<Perfil>();
		this.senha = Criptografia.sha256("mude-me");
		this.ativado = true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	public boolean isAtivado() {
		return ativado;
	}

	public void setAtivado(boolean ativado) {
		this.ativado = ativado;
	}
	
	public void adicionarPerfil(Perfil perfil){
		getPerfis().clear();
		if(perfil != null && !getPerfis().contains(perfil)){
			getPerfis().add(perfil);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
	public boolean isADMIN(){
		return temPerfil(Perfil.PERFIL_ADMIN);
	}
	
	public boolean isSECRETARIA(){
		return temPerfil(Perfil.PERFIL_SECRETARIA);
	}
	
	public boolean isGOP(){
		return temPerfil(Perfil.PERFIL_GOP);
	}
	
	public boolean isCOORDENADOR(){
		return temPerfil(Perfil.PERFIL_COORDENADOR);
	}
	
	private boolean temPerfil(String nomePerfil){
		for(Perfil p : perfis){
			if(p.getNome().equals(nomePerfil)){
				return true;
			}
		}
		return false;
	}

}
