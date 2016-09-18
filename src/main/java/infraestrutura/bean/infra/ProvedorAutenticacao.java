package infraestrutura.bean.infra;

import infraestrutura.model.Usuario;
import infraestrutura.service.UsuarioService;
import infraestrutura.util.Criptografia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ProvedorAutenticacao implements AuthenticationProvider{

	@Autowired
	private UsuarioService usuarioService;
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		Authentication authenticator = null;
		Usuario usuario = usuarioService.login(authentication.getName(), Criptografia.sha256(authentication.getCredentials().toString()));
		if(usuario != null){
			return new UsernamePasswordAuthenticationToken(usuario,usuario.getSenha(), usuario.getPerfis());
		}
		return authenticator;
	}

	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
