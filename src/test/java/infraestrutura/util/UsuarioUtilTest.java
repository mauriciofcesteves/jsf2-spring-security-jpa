package infraestrutura.util;

import infraestrutura.model.Perfil;
import infraestrutura.model.Usuario;

public class UsuarioUtilTest {

	public static Usuario criar(Perfil perfil) {
		Usuario usuario = new Usuario();
		usuario.setLogin("teste");
		usuario.setSenha("teste");
		usuario.getPerfis().add(perfil);
		return usuario;
	}
	
}
