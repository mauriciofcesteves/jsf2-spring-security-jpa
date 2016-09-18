package infraestrutura.dao;

import infraestrutura.model.Caderneta;
import infraestrutura.model.SituacaoCaderneta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class CadernetasDao extends GenericPojoDao<Caderneta> {

	@Override
	public Class<Caderneta> getPersistentClass() {
		return Caderneta.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Caderneta> consultarPorSituacao(SituacaoCaderneta situacao){
		Criteria criteria = criarCriteria(Caderneta.class, null);
		criteria.add(Restrictions.eq("situacao", situacao));
		return criteria.list();
	}
}
