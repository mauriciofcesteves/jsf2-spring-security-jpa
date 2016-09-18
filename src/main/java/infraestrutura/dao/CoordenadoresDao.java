package infraestrutura.dao;

import infraestrutura.bean.infra.Filtro;
import infraestrutura.model.Coordenador;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CoordenadoresDao extends GenericPojoDao<Coordenador> {

	@Override
	public Class<Coordenador> getPersistentClass() {
		return Coordenador.class;
	}
	
	@Override
	public List<Coordenador> listar() {
		Filtro filtro = new Filtro();
		filtro.addOrder("nome");
		return consultarPorFiltro(filtro);
	}

}
