package infraestrutura.service;

import infraestrutura.bean.infra.Filtro;
import infraestrutura.bean.infra.Filtro.TipoFiltro;
import infraestrutura.bean.infra.Filtro.ValorFiltro;
import infraestrutura.dao.CoordenadoresDao;
import infraestrutura.exception.ErrorMessage;
import infraestrutura.exception.NegocioException;
import infraestrutura.model.Coordenador;
import infraestrutura.model.Perfil;
import infraestrutura.model.Usuario;
import infraestrutura.util.CadernetaUtil;
import infraestrutura.util.MessageResourcesConstants;

import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoordenadoresService extends GenericPojoService<Coordenador> {
	@Autowired
	private CoordenadoresDao coordenadoresDao;
	@Autowired
	private PerfilService perfilService;

	public CoordenadoresDao getDAO() {
		return coordenadoresDao;
	}
	
	@Override
	public void salvar(Coordenador coordenador) {
		NegocioException exception = new NegocioException();
		
		if(StringUtil.isEmpty(coordenador.getMatricula())){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.MATRICULA);
		}else{
			Filtro filtro = new Filtro();
			filtro.put("matricula", ValorFiltro.get(TipoFiltro.VALOR_EXATO,coordenador.getMatricula()));
			filtro.put("id", ValorFiltro.get(TipoFiltro.NEGACAO,coordenador.getId()));
			if(!consultarPorFiltro(filtro).isEmpty()){
				exception.addMessage(ErrorMessage.CAMPO_JA_CADASTRADO, MessageResourcesConstants.MATRICULA);
			}
		}
		
		if(StringUtil.isEmpty(coordenador.getNome())){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.NOME);
		}
		
		if(StringUtil.isEmpty(coordenador.getEmail())){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.EMAIL);
		}else if(!CadernetaUtil.validarEmail(coordenador.getEmail())){
			exception.addMessage(ErrorMessage.ERRO_EMAIL_INVALIDO, coordenador.getEmail());
		}
		
		if(exception.hasException()){
			throw exception;
		}
		
		Usuario usuario = coordenador.recuperarUsuario();
		usuario.adicionarPerfil(perfilService.consultarPorNome(Perfil.PERFIL_COORDENADOR));
		super.salvar(coordenador);
	}

	public void setCoordenadoresDao(CoordenadoresDao coordenadoresDao) {
		this.coordenadoresDao = coordenadoresDao;
	}

	public PerfilService getPerfilService() {
		return perfilService;
	}

	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}
	
}
