package infraestrutura.service;

import java.util.List;

import infraestrutura.bean.infra.Filtro;
import infraestrutura.dao.UsuarioDao;
import infraestrutura.exception.ErrorMessage;
import infraestrutura.exception.NegocioException;
import infraestrutura.model.Usuario;
import infraestrutura.util.Criptografia;
import infraestrutura.util.MessageResourcesConstants;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages;

import sun.security.jgss.spi.MechanismFactory;

@Component
public class UsuarioService extends GenericService<Usuario> {

	@Autowired
	private UsuarioDao usuarioDao;

	public UsuarioDao getDAO() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	/**
	 * Consulta o usu�rio logado pelo login informado.
	 */
	public Usuario consultarUsuarioPorLogin(String login) {
		return usuarioDao.consultarPorLogin(login);
	}
	
	public Usuario login(String login, String senha){
		return usuarioDao.login(login, senha);
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}
	
	@Override
	public void salvar(Usuario usuario) {
		NegocioException exception = new NegocioException();
		
		if(StringUtils.isEmpty(usuario.getLogin())){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.LOGIN);
		}
		
		if(StringUtils.isEmpty(usuario.getNome())){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.NOME);
		}
		
		if(usuario.getPerfis() == null || usuario.getPerfis().isEmpty()){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.PERFIL);
		}
		
		if(exception.hasException()){
			throw exception;
		}
		
		super.salvar(usuario);
	}
	
	public void salvarSenha(Usuario usuario, String senha, String confirmacao){
		NegocioException negocioException = new NegocioException();
		
		if(StringUtils.isBlank(senha)){
			negocioException.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.SENHA );
		}
		
		if(!senha.equals(confirmacao)){
			negocioException.addMessage(ErrorMessage.SENHA_NAO_CONFEREM);
		}
		
		if(negocioException.hasException()) {
			throw new NegocioException();
		}
		
		usuario.setSenha(Criptografia.sha256(senha));
		super.salvar(usuario);
	}
	
	@Override
	public List<Usuario> listar() {
		Filtro filtro = new Filtro();
		filtro.addOrder("nome");
		filtro.addOrder("login");
		return consultarPorFiltro(filtro);
	}
}
