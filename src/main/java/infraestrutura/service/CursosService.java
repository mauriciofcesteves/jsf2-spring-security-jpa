package infraestrutura.service;

import infraestrutura.dao.CursosDao;
import infraestrutura.dao.GenericDao;
import infraestrutura.exception.ErrorMessage;
import infraestrutura.exception.NegocioException;
import infraestrutura.model.Curso;
import infraestrutura.util.MessageResourcesConstants;

import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CursosService extends GenericPojoService<Curso> {

	@Autowired
	private CursosDao cursosDao;
	
	@Override
	public void salvar(Curso curso) {
		NegocioException exception = new NegocioException();
		
		if(StringUtil.isEmpty(curso.getCodigo())){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.CODIGO);
		}
		
		if(StringUtil.isEmpty(curso.getNome())){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.NOME);
		}
		
		if(curso.getCoordenador() == null){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.COORDENADOR);
		}
		
		if(curso.getDisciplinas().isEmpty()){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.DISCIPLINAS);
		}
		
		if(exception.hasException()){
			throw exception;
		}
		
		super.salvar(curso);
	}

	@Override
	public GenericDao<Curso> getDAO() {
		return cursosDao;
	}

	public CursosDao getCursosDao() {
		return cursosDao;
	}

	public void setCursosDao(CursosDao cursosDao) {
		this.cursosDao = cursosDao;
	}
	
}
