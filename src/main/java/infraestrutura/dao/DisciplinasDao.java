package infraestrutura.dao;

import infraestrutura.bean.infra.Filtro;
import infraestrutura.model.Disciplina;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DisciplinasDao extends GenericPojoDao<Disciplina> {

	@Override
	public Class<Disciplina> getPersistentClass() {
		return Disciplina.class;
	}
	
	@Override
	public List<Disciplina> listar() {
		Filtro filtro = new Filtro();
		filtro.addOrder("nome");
		return consultarPorFiltro(filtro);
	}

}
