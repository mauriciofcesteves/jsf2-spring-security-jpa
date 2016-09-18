package infraestrutura.service;

import infraestrutura.dao.ProfessoresDao;
import infraestrutura.exception.ErrorMessage;
import infraestrutura.exception.NegocioException;
import infraestrutura.model.Professor;
import infraestrutura.util.MessageResourcesConstants;

import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessoresService extends GenericPojoService<Professor> {

	@Autowired
	private ProfessoresDao professoresDao;
	
	@Override
	public void salvar(Professor professor) {
		NegocioException exception = new NegocioException();

		if(StringUtil.isEmpty(professor.getNome())){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.NOME);
		}

		if(StringUtil.isEmpty(professor.getSeguradora())){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.SEGURADORA);
		}
		
		if(exception.hasException()){
			throw exception;
		}
		
		super.salvar(professor);
	}
	
	public ProfessoresDao getDAO() {
		return professoresDao;
	}

	public void setProfessoresDao(ProfessoresDao professoresDao) {
		this.professoresDao = professoresDao;
	}

	public ProfessoresDao getProfessoresDao() {
		return professoresDao;
	}
	
}
