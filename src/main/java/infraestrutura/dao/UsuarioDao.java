package infraestrutura.dao;

import infraestrutura.exception.ErrorMessage;
import infraestrutura.exception.NegocioException;
import infraestrutura.model.Usuario;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDao extends GenericDao<Usuario> {

	@Override
	public Class<Usuario> getPersistentClass() {
		return Usuario.class;
	}

	/**
	 * Retorna o Usuario de acordo com o login informado.
	 */
	public Usuario consultarPorLogin(String login) {
		Usuario usuario = null;
		Criteria criteria = criarCriteria(Usuario.class, null);
		criteria.add(Restrictions.eq("login", login));
		try {
			usuario = (Usuario) criteria.uniqueResult();
		} catch (Exception e) {
			usuario = null;
		}
		return usuario;
	}
	
	public Usuario login(String login, String senha){
		Usuario usuario = null;
		Criteria criteria = criarCriteria(Usuario.class, null);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("senha", senha));
		try {
			usuario = (Usuario) criteria.uniqueResult();
		} catch (Exception e) {
			usuario = null;
		}
		return usuario;
	}
	
	@Override
	public void excluir(Usuario usuario) {
		try{
			super.excluir(usuario);
		}catch(Throwable e){
			throw new NegocioException(ErrorMessage.USUARIO_NAO_PODE_SER_DELETADO);
		}
	}
	
	
}
