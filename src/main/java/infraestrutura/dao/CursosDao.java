package infraestrutura.dao;

import infraestrutura.bean.infra.Filtro;
import infraestrutura.model.Curso;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CursosDao extends GenericPojoDao<Curso> {

	@Override
	public Class<Curso> getPersistentClass() {
		return Curso.class;
	}
	
	@Override
	public List<Curso> listar() {
		Filtro filtro = new Filtro();
		filtro.addOrder("nome");
		return consultarPorFiltro(filtro);
	}
	
}
