package infraestrutura.service;

import infraestrutura.bean.infra.Filtro;
import infraestrutura.dao.GenericDao;

import java.util.Collection;
import java.util.List;

public abstract class GenericService<T> {

	public abstract GenericDao<T> getDAO();
	
	public void salvar(T obj) {
		getDAO().salvar(obj);
	}
	
	public T consultarPorId(Integer id) {
		return (T) getDAO().consultarPorId(id);
	}
	
	public void excluir(T obj) {
		getDAO().excluir(obj);
	}
	
	public void excluir(Collection<T> objs) {
		for(T t : objs){
			excluir(t);
		}
	}

	public List<T> listar() {
		return getDAO().listar();
	}
	
	public List<T> consultarPorFiltro(Filtro filter) {
		return getDAO().consultarPorFiltro(filter);
	}
	
}