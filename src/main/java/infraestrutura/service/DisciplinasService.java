package infraestrutura.service;

import infraestrutura.dao.DisciplinasDao;
import infraestrutura.exception.ErrorMessage;
import infraestrutura.exception.NegocioException;
import infraestrutura.model.Disciplina;
import infraestrutura.util.MessageResourcesConstants;

import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisciplinasService extends GenericPojoService<Disciplina> {

	@Autowired
	private DisciplinasDao disciplinasDao;
	
	@Override
	public void salvar(Disciplina disciplina) {
		
		NegocioException exception = new NegocioException();
		
		if(StringUtil.isEmpty(disciplina.getCodigo())){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.CODIGO);
		}
		
		if(StringUtil.isEmpty(disciplina.getNome())){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.NOME);
		}
		
		if(exception.hasException()){
			throw exception;
		}
		
		super.salvar(disciplina);
	}
	
	public DisciplinasDao getDAO() {
		return disciplinasDao;
	}

	public DisciplinasDao getDisciplinasDao() {
		return disciplinasDao;
	}

	public void setDisciplinasDao(DisciplinasDao disciplinasDao) {
		this.disciplinasDao = disciplinasDao;
	}
	
}
