package infraestrutura.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Coordenador extends Funcionario {

	private static final long serialVersionUID = 1L;
	
	@OneToOne(fetch=FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario recuperarUsuario(){
		if(usuario == null){
			usuario = new Usuario();
			usuario.setNome(getNome());
		}
		usuario.setLogin(getMatricula());
		return usuario;
	}

}
