package infraestrutura.service;

import infraestrutura.model.infra.Pojo;

public abstract class GenericPojoService<T extends Pojo> extends GenericService<T>{

	@Override
	public void excluir(T obj) {
		try{
			super.excluir(obj);
		}
		catch(Throwable e){
			obj.setAtivo(false);
			super.salvar(obj);
		}
	}
	
}