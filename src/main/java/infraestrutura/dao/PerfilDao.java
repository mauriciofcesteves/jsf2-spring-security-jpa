package infraestrutura.dao;

import infraestrutura.model.Perfil;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class PerfilDao extends GenericDao<Perfil> {

	@Override
	public Class<Perfil> getPersistentClass() {
		return Perfil.class;
	}
	
	public Perfil consultarPorNome(String nome) {
		Perfil perfil;
		Criteria criteria = criarCriteria(Perfil.class, "perfil");
		criteria.add(Restrictions.eq("perfil.nome", nome));
		try {
			perfil = (Perfil) criteria.uniqueResult();
		} catch (Exception e) {
			perfil = null;
		}
		return perfil;
	}

}
