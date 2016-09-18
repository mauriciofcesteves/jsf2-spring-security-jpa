package infraestrutura.dao;

import infraestrutura.model.ParametroSistema;

import org.springframework.stereotype.Component;

@Component
public class ParametroSistemaDao extends GenericPojoDao<ParametroSistema>{

	@Override
	public Class<ParametroSistema> getPersistentClass() {
		return ParametroSistema.class;
	}

}
