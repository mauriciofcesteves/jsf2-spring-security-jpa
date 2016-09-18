package infraestrutura.dao;

import infraestrutura.bean.infra.Filtro;
import infraestrutura.model.infra.Pojo;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class GenericPojoDao<T extends Pojo> extends GenericDao<T>{

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listar() {
		return entityManager.createQuery("select t from " + getNomeTabela() + " t where t.ativo = true").getResultList();
	}
	
	@Override
	public List<T> consultarPorFiltro(Filtro filter) {
		filter.put("ativo", true);
		return super.consultarPorFiltro(filter);
	}
	
}
