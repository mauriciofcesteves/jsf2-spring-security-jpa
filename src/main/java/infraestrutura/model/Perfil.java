package infraestrutura.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final String PERFIL_ADMIN = "ROLE_ADMIN";
	public static final String PERFIL_SECRETARIA = "ROLE_SECRETARIA";
	public static final String PERFIL_GOP = "ROLE_GOP";
	public static final String PERFIL_COORDENADOR = "ROLE_COORDENADOR";

	@Id
	@Column(length=50)
	private String nome;
	
	@Column(length=50)
	private String descricao;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Transient
	public String getAuthority() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Perfil other = (Perfil) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public boolean isAdmin(){
		return PERFIL_ADMIN.equals(nome);
	}
	
	public boolean isSecretaria(){
		return PERFIL_SECRETARIA.equals(nome);
	}
	
	public boolean isGOP(){
		return PERFIL_GOP.equals(nome);
	}
	
	public boolean isCoordenador(){
		return PERFIL_COORDENADOR.equals(nome);
	}
	
}
