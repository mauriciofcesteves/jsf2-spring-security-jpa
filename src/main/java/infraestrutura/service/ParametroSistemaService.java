package infraestrutura.service;

import infraestrutura.dao.ParametroSistemaDao;
import infraestrutura.exception.ErrorMessage;
import infraestrutura.exception.MensagemNegocio;
import infraestrutura.exception.NegocioException;
import infraestrutura.model.ParametroSistema;
import infraestrutura.util.CadernetaUtil;
import infraestrutura.util.MessageResourcesConstants;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParametroSistemaService extends GenericPojoService<ParametroSistema>{

	@Autowired
	private ParametroSistemaDao parametroSistemaDao;
	
	@Override
	public ParametroSistemaDao getDAO() {
		return parametroSistemaDao;
	}
	
	@Override
	public void salvar(ParametroSistema parametroSistema) {
		validarCampoObrigatorioMenuParametro(parametroSistema);
		super.salvar(parametroSistema);
	}
	
	public void salvarEmail(ParametroSistema parametroSistema){
		NegocioException negocioException = new NegocioException();
		
		if(StringUtils.isBlank(parametroSistema.getEmail())){
			negocioException.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.EMAIL);
		}
		
		if(StringUtils.isBlank(parametroSistema.getSenha())){
			negocioException.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.SENHA);
		}
		
		if(StringUtils.isBlank(parametroSistema.getSmtp())){
			negocioException.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.SMTP);
		}
		
		if(StringUtils.isBlank(parametroSistema.getPortaSmtp())){
			negocioException.addMessage(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.SMTP_PORTA);
		}
		
		if(negocioException.hasException()){
			throw negocioException;
		}
		
		atribuirValoresDefaultParametro(parametroSistema);
		super.salvar(parametroSistema);
	}
	
	public void salvarTemplates(ParametroSistema parametroSistema){
		validarTemplateEmail(parametroSistema);
		atribuirValoresDefaultParametro(parametroSistema);
		super.salvar(parametroSistema);
	}
	
	private void atribuirValoresDefaultParametro(ParametroSistema parametroSistema) {
		if (StringUtils.isBlank(parametroSistema.getIntervaloDiasParaEnvioEmail())) {
			parametroSistema.setIntervaloDiasParaEnvioEmail(ParametroSistema.INTERVALO_ENVIO_EMAILS_DEFAULT);
		}

		if (StringUtils.isBlank(parametroSistema.getPrazoCoordenador())) {
			parametroSistema.setPrazoCoordenador(ParametroSistema.PRAZO_COORDENADOR_DEFAULT);
		}
		
		if (StringUtils.isBlank(parametroSistema.getPrazoProfessor())) {
			parametroSistema.setPrazoProfessor(ParametroSistema.PRAZO_PROFESSOR_DEFAULT);
		}
	}
	
	public void validarCampoObrigatorioMenuParametro(
			ParametroSistema parametro) {
		List<MensagemNegocio> erros = new ArrayList<MensagemNegocio>();
		if (StringUtils.isBlank(parametro.getIntervaloDiasParaEnvioEmail())) {
			erros.add(new MensagemNegocio(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.PRAZO_PROFESSOR));
		}
		
		if (StringUtils.isBlank(parametro.getPrazoCoordenador())) {
			erros.add(new MensagemNegocio(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.PRAZO_COORDENADOR));
		}
		
		if (StringUtils.isBlank(parametro.getPrazoProfessor())) {
			erros.add(new MensagemNegocio(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.INTERVALO_DIAS_EMAIL));
		}
		
		if (StringUtils.isBlank(parametro.getEmailGOP())) {
			erros.add(new MensagemNegocio(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, MessageResourcesConstants.EMAIL_GOP));
		}else if(!CadernetaUtil.validarEmail(parametro.getEmailGOP())){
			erros.add(new MensagemNegocio(ErrorMessage.ERRO_EMAIL_INVALIDO, parametro.getEmailGOP()));
		}
		
		if (!erros.isEmpty()) {
			throw new NegocioException(erros);
		}
	}

	public ParametroSistema getParametroSistema(){
		List<ParametroSistema> parametroSistemas = parametroSistemaDao.listar(); 
		ParametroSistema parametroSistema = null;
		if(!parametroSistemas.isEmpty()){
			parametroSistema = parametroSistemas.get(0);
		}
		return parametroSistema;
	}

	public ParametroSistemaDao getParametroSistemaDao() {
		return parametroSistemaDao;
	}

	public void setParametroSistemaDao(ParametroSistemaDao parametroSistemaDao) {
		this.parametroSistemaDao = parametroSistemaDao;
	}

	/**
	 * Esta validação se certificará de não dar erro, caso o usuário resolva tentar salvar alguns templates
	 * e deixar outros em branco.
	 */
	private void validarTemplateEmail(ParametroSistema parametro) {
		List<MensagemNegocio> erros = new ArrayList<MensagemNegocio>();
		if (!CadernetaUtil.isTemplatePreenchido(parametro.getInformarProtocoloCaderneta())) {
			erros.add(new MensagemNegocio(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, "template_email_para_protocolo"));
		}

		if (!CadernetaUtil.isTemplatePreenchido(parametro.getSolicitarDevolucaoCaderneta())) {
			erros.add(new MensagemNegocio(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, "template_email_solicitar_devolucao_caderneta"));
		}
		
		if (!CadernetaUtil.isTemplatePreenchido(parametro.getInformarPrazoExtrapolado())) {
			erros.add(new MensagemNegocio(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, "template_email_professor_extrapolou_prazo"));
		}

		if (!CadernetaUtil.isTemplatePreenchido(parametro.getInformarCoordenadorCadernetaDisponivel())) {
			erros.add(new MensagemNegocio(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, "template_email_caderneta_disponivel_coordenador"));
		}

		if (!CadernetaUtil.isTemplatePreenchido(parametro.getInformarGestorOperacional())) {
			erros.add(new MensagemNegocio(ErrorMessage.ERRO_CAMPO_OBRIGATORIO, "template_email_gestor_operacional"));
		}
		
		if (!erros.isEmpty()) {
			throw new NegocioException(erros);
		}
	}
}
