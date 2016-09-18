package infraestrutura.service;

import infraestrutura.dao.PerfilDao;
import infraestrutura.model.Perfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PerfilService extends GenericService<Perfil> {

	@Autowired
	private PerfilDao perfilDao;

	public PerfilDao getDAO() {
		return perfilDao;
	}
	
	public Perfil consultarPorNome(String nome) {
		return perfilDao.consultarPorNome(nome);
	}

	public PerfilDao getPerfilDao() {
		return perfilDao;
	}

	public void setPerfilDao(PerfilDao perfilDao) {
		this.perfilDao = perfilDao;
	}

}
