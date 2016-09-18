package infraestrutura.dao;

import infraestrutura.bean.infra.Filtro;
import infraestrutura.model.Professor;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProfessoresDao extends GenericPojoDao<Professor> {

	@Override
	public Class<Professor> getPersistentClass() {
		return Professor.class;
	}
	
	@Override
	public List<Professor> listar() {
		Filtro filtro = new Filtro();
		filtro.addOrder("nome");
		return consultarPorFiltro(filtro);
	}

}
