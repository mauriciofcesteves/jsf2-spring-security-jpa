package infraestrutura.service;

import infraestrutura.bean.infra.Filtro;
import infraestrutura.dao.CadernetasDao;
import infraestrutura.dao.GenericDao;
import infraestrutura.exception.ErrorMessage;
import infraestrutura.exception.NegocioException;
import infraestrutura.model.Caderneta;
import infraestrutura.model.ParametroSistema;
import infraestrutura.model.SituacaoCaderneta;
import infraestrutura.model.TemplateEmail;
import infraestrutura.model.Usuario;
import infraestrutura.util.CadernetaUtil;
import infraestrutura.util.Criptografia;
//import infraestrutura.util.EmailUtil;
import infraestrutura.util.MessageResourcesConstants;

import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadernetasService extends GenericPojoService<Caderneta> {

	@Autowired
	private CadernetasDao cadernetaDao;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ParametroSistemaService parametroSistemaService;

	@Override
	public GenericDao<Caderneta> getDAO() {
		return cadernetaDao;
	}

	public CadernetasDao getCadernetaDao() {
		return cadernetaDao;
	}

	public void setCadernetaDao(CadernetasDao cadernetaDao) {
		this.cadernetaDao = cadernetaDao;
	}
	
	public Long gerarProtocolo(){
		try{
			return Long.valueOf(CadernetaUtil.formatadorDataProtocolo.format(new Date()));
		}catch(Exception e){
			return 0L;
		}
	}
	
	//Lista as cadernetas ordenando das mais novas para as mais antigas
	public List<Caderneta> listarOrdenada(){
		Filtro filtro = new Filtro();
		filtro.addOrder("protocolo", false);
		return consultarPorFiltro(filtro);
	}

	public List<Caderneta> consultarPorSituacao(SituacaoCaderneta situacao) {
		return cadernetaDao.consultarPorSituacao(situacao);
	}
	
	@Override
	public void salvar(Caderneta caderneta) {
		NegocioException exception =  new NegocioException();
		
		if(parametroSistemaService.getParametroSistema() == null){
			exception.addMessage(ErrorMessage.PARAMETRO_SISTEMA_NAO_CONFIGURADO);
		}
		
		if(caderneta.getDataRetiradaProfessor() == null){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.DATA_RETIRADA_PROFESSOR);
		}else if(caderneta.getDataRetiradaProfessor().isAfter(new LocalDate())){
			exception.addMessage(ErrorMessage.ERRO_DATA_MAIOR_ATUAL, MessageResourcesConstants.DATA_RETIRADA_PROFESSOR);
		}
		if(caderneta.getCurso() == null){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.CURSO);
		}
		if(caderneta.getCoordenador() == null){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.COORDENADOR);
		}
		if(caderneta.getProfessor() == null){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.PROFESSOR);
		}
		if(caderneta.getDisciplina() == null){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.DISCIPLINA);
		}
		if(caderneta.getDataFinalDisciplina() == null && caderneta.getId() == null){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.FIM_DISCIPLINA);
		}else if(caderneta.getDataFinalDisciplina().isBefore(new LocalDate()) && caderneta.getId() == null){
			exception.addMessage(ErrorMessage.ERRO_DATA_MENOR_ATUAL, MessageResourcesConstants.FIM_DISCIPLINA);
		}
		
		if(exception.hasException()){
			throw exception;
		}
		
		super.salvar(caderneta);
	}
	
	public void registrarDevolucaoProfessor(Caderneta caderneta){
		NegocioException exception =  new NegocioException();
		
		if(caderneta.getDataDevolucaoProfessor() == null){
			exception.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.DATA_DEVOLUCAO_PROFESSOR);
		}else if(caderneta.getDataDevolucaoProfessor().isBefore(caderneta.getDataFinalDisciplina())){
			exception.addMessage(ErrorMessage.ERRO_DEVOLUCAO_ANTES_TERMINO_DISCIPLINA, MessageResourcesConstants.DATA_DEVOLUCAO_PROFESSOR);
		}else if(caderneta.getDataDevolucaoProfessor().isAfter(new LocalDate())){
			exception.addMessage(ErrorMessage.ERRO_DATA_MAIOR_ATUAL, MessageResourcesConstants.DATA_DEVOLUCAO_PROFESSOR);
		}
		
		if(exception.hasException()){
			throw exception;
		}
		
		//enviar e-mail para o professor informando o protocolo de entrega
		ParametroSistema parametro = parametroSistemaService.getParametroSistema();
		TemplateEmail template =   CadernetaUtil.mergeTemplateInformarProtocoloCadernetaProfessor(parametro.getInformarProtocoloCaderneta(), caderneta, false);
//		EmailUtil.enviarEmailTratandoErro(parametro, template.getAssunto(), template.getCorpo(), caderneta.getProfessor().getEmail());
		
		caderneta.setSituacao(SituacaoCaderneta.AGUARDANDO_COORDENADOR);
		salvar(caderneta);
	}
	
	public void registrarDevolucaoCoordenador(Caderneta caderneta){
		NegocioException negocioException = new NegocioException();
		
		if(caderneta.getDataDevolucaoCoordenador() == null){
			negocioException.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.DATA_DEVOLUCAO_COORDENADOR);
		}else if(caderneta.getDataDevolucaoCoordenador().isAfter(new LocalDate())){
			negocioException.addMessage(ErrorMessage.ERRO_DATA_MAIOR_ATUAL, MessageResourcesConstants.DATA_DEVOLUCAO_COORDENADOR);
		}
		
		if(negocioException.hasException()){
			throw negocioException;
		}
		
		ParametroSistema parametroSistema = parametroSistemaService.getParametroSistema();
		
		ParametroSistema parametro = parametroSistemaService.getParametroSistema();
		TemplateEmail template =  CadernetaUtil.mergeTemplateInformarProtocoloCadernetaCoordenador(parametro.getInformarProtocoloCaderneta(), caderneta, false);
		//Enviar e-mail para o coordenador informando o protocolo da devolu��o
//		EmailUtil.enviarEmailTratandoErro(parametroSistema, template.getAssunto(), template.getCorpo(), caderneta.getCoordenador().getEmail());
		template = CadernetaUtil.mergeTemplateInformarGestorOperacional(parametro.getInformarGestorOperacional(), caderneta);
		//Enviar e-mail para o GOP informando a devolu��o da caderneta com as notas
//		EmailUtil.enviarEmailTratandoErro(parametroSistema, template.getAssunto(), template.getCorpo(), parametroSistema.getEmailGOP());
		
		caderneta.setSituacao(SituacaoCaderneta.CONCLUIDO);
		salvar(caderneta);
	}
	
	public void registrarRetiradaCoordenador(Caderneta caderneta, String loginCoordenador, String senhaCoordenador){
		Usuario usuarioCoordenador = usuarioService.login(loginCoordenador, Criptografia.sha256(senhaCoordenador));
		
		NegocioException negocioException = new NegocioException();
		
		if(caderneta.getDataRetiradaCoordenador() == null){
			negocioException.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.DATA_RETIRADA_COORDENADOR);
		}else if(caderneta.getDataRetiradaCoordenador().isAfter(new LocalDate())){
			negocioException.addMessage(ErrorMessage.ERRO_DATA_MAIOR_ATUAL, MessageResourcesConstants.DATA_RETIRADA_COORDENADOR);
		}
		
		if(usuarioCoordenador == null || !caderneta.getCoordenador().getUsuario().equals(usuarioCoordenador)){
			negocioException.addMessage(ErrorMessage.ERRO_COORDENADOR_INVALIDO);
		}
	
		if(negocioException.hasException()){
			throw negocioException;
		}
		
		caderneta.setSituacao(SituacaoCaderneta.CADERNETA_COM_COORDENADOR);
		salvar(caderneta);
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public ParametroSistemaService getParametroSistemaService() {
		return parametroSistemaService;
	}

	public void setParametroSistemaService(
			ParametroSistemaService parametroSistemaService) {
		this.parametroSistemaService = parametroSistemaService;
	}
	
	
}
