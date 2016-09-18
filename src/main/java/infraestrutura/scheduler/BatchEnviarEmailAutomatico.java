package infraestrutura.scheduler;

import infraestrutura.exception.IMessage;
import infraestrutura.model.Caderneta;
import infraestrutura.model.ParametroSistema;
import infraestrutura.model.SituacaoCaderneta;
import infraestrutura.model.TemplateEmail;
import infraestrutura.service.CadernetasService;
import infraestrutura.service.ParametroSistemaService;
import infraestrutura.util.CadernetaUtil;
//import infraestrutura.util.EmailUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Classe (batch) responsavel pelo envio de e-mail automaticamente.
 * Programado para rodar apenas uma vez ao dia.
 */
@Service
public class BatchEnviarEmailAutomatico {

	@Autowired
	private ParametroSistemaService parametroSistemaService;
	
	@Autowired
	private CadernetasService cadernetasService;
	
	/**
	 * Metodo que inicializa o batch.
	 */
	@Scheduled(cron = "0 23 19 * * ?") //configurado para rodar todos os dias a partir das 20:13 AM
	public void run() {
		System.out.println("####################### INICIO DA EXECUCAO ROTINA BATCH #######################");
		LocalDate hoje = new LocalDate();
		ParametroSistema parametro = parametroSistemaService.getParametroSistema();

		enviarEmailCadernetasComProfessor(hoje, parametro);
		enviarEmailCadernetasAguardandoCoordenador(hoje, parametro);
		enviarEmailCadernetasComCoordenador(hoje, parametro);
		System.out.println("####################### FIM DA EXECUCAO DA ROTINA BATCH #######################");
	}

	/**
	 * Este m�todo envia e-mails para os professores cobrando a devolu��o da caderneta.
	 * Este m�todo continuar� enviando e-mails cobrando a devolu��o do professor, mesmo ap�s extrapolar a data.
	 * Ap�s extrapolar a data, e-mails tamb�m ser�o enviados ao coordenador informando do atraso do professor.
	 * Os e-mails ser�o enviados de acordo com o intervalo estabelecido pelo usu�rio na parametriza��o do sistema. 
	 */
	private void enviarEmailCadernetasComProfessor(LocalDate hoje, ParametroSistema parametro) {
		List<Caderneta> cadernetasComProfessor = cadernetasService.consultarPorSituacao(SituacaoCaderneta.CADERNETA_COM_PROFESSOR);
		Map<String, IMessage> relatorioEmailsEnviados = new HashMap<String, IMessage>();
		
		for (Caderneta caderneta : cadernetasComProfessor) {
			boolean enviarEmail = isEnviarEmailHoje(hoje, caderneta.getDataFinalDisciplina(), caderneta.getDataPrevistaDevolucaoProfessor(), Integer.valueOf(parametro.getIntervaloDiasParaEnvioEmail()));
			if (enviarEmail) {
				//envia email para o professor solicitando devolucao da caderneta
				TemplateEmail templateComMerge = CadernetaUtil.mergeTemplateSolicitarDevolucaoCaderneta(parametro.getSolicitarDevolucaoCaderneta(), caderneta, true);
//				EmailUtil.enviarEmail(parametro, templateComMerge, relatorioEmailsEnviados, caderneta.getProfessor().getEmail());
			}

			enviarEmail = isEnviarEmailComPrazoExtrapolado(hoje, caderneta.getDataPrevistaDevolucaoProfessor());
			if (enviarEmail) {
				//o prazo extrapolou e esse email sera enviado ao coordenador informando o atraso do professor
				//esse email continuara enviando pra o coordenador enquanto o professor n devolver
				TemplateEmail templateComMerge = CadernetaUtil.mergeTemplateInformarPrazoExtrapolado(parametro.getInformarPrazoExtrapolado(), caderneta);
//				EmailUtil.enviarEmail(parametro, templateComMerge, relatorioEmailsEnviados, caderneta.getCoordenador().getEmail());

				//envia email para o professor pra devolver a caderneta, o prazo ja esta extrapolado e o email continuara enviando
				templateComMerge = CadernetaUtil.mergeTemplateSolicitarDevolucaoCaderneta(parametro.getSolicitarDevolucaoCaderneta(), caderneta, true);
//				EmailUtil.enviarEmail(parametro, templateComMerge, relatorioEmailsEnviados, caderneta.getProfessor().getEmail());
			}
		}
		
		//Somente envia o relatorio se houver mensagens. Evitar de enviar e-mails de relatorios vazios.
		if (!relatorioEmailsEnviados.isEmpty()) {
			enviarEmailRelatorioBatch(relatorioEmailsEnviados, parametro, "Relatorio de envio de e-mails para 'Professor com Caderneta'");
		}
	}

	/**
	 * Este m�todo envia e-mails para os coordenadores informando que a caderneta j� est� dispon�vel para retirada.
	 * Este m�todo continuar� enviando e-mails cobrando a retirada do coordenador, mesmo ap�s extrapolar a data.
	 * Os e-mails ser�o enviados de acordo com o intervalo estabelecido pelo usu�rio na parametriza��o do sistema. 
	 */
	private void enviarEmailCadernetasAguardandoCoordenador(LocalDate hoje, ParametroSistema parametro) {
		List<Caderneta> cadernetasAguardandoCoordenador = cadernetasService.consultarPorSituacao(SituacaoCaderneta.AGUARDANDO_COORDENADOR);
		Map<String, IMessage> relatorioEmailsEnviados = new HashMap<String, IMessage>();

		for (Caderneta caderneta : cadernetasAguardandoCoordenador) {
			boolean enviarEmail = isEnviarEmailHoje(hoje, caderneta.getDataDevolucaoProfessor(), caderneta.getDataPrevistaDevolucaoCoordenador(), Integer.valueOf(parametro.getIntervaloDiasParaEnvioEmail()));
			if (enviarEmail) {
				//envia email para o professor solicitando devolucao da caderneta
				TemplateEmail templateComMerge = CadernetaUtil.mergeTemplateInformarCoordenadorCadernetaDisponivel(parametro.getInformarCoordenadorCadernetaDisponivel(), caderneta);
//				EmailUtil.enviarEmail(parametro, templateComMerge, relatorioEmailsEnviados, caderneta.getCoordenador().getEmail());
			}

			enviarEmail = isEnviarEmailComPrazoExtrapolado(hoje, caderneta.getDataPrevistaDevolucaoCoordenador());
			if (enviarEmail) {
				//envia email para o coordenador pra devolver a caderneta, o prazo ja esta extrapolado e o email continuara enviando
				TemplateEmail templateComMerge = CadernetaUtil.mergeTemplateInformarCoordenadorCadernetaDisponivel(parametro.getInformarCoordenadorCadernetaDisponivel(), caderneta);
//				EmailUtil.enviarEmail(parametro, templateComMerge, relatorioEmailsEnviados, caderneta.getCoordenador().getEmail());
			}
		}
		
		//Somente envia o relatorio se houver mensagens. Evitar de enviar e-mails de relatorios vazios.
		if (!relatorioEmailsEnviados.isEmpty()) {
			enviarEmailRelatorioBatch(relatorioEmailsEnviados, parametro, "Relatorio de envio de e-mails para 'Caderneta Aguardando Coordenador'");
		}
	}

	/**
	 * Este m�todo envia e-mails para os coordenadores cobrando a devolu��o da caderneta.
	 * Este m�todo continuar� enviando e-mails cobrando a devolu��o dos coordenadores, mesmo ap�s extrapolar a data.
	 * Os e-mails ser�o enviados de acordo com o intervalo estabelecido pelo usu�rio na parametriza��o do sistema. 
	 */
	private void enviarEmailCadernetasComCoordenador(LocalDate hoje, ParametroSistema parametro) {
		List<Caderneta> cadernetasComCoordenador = cadernetasService.consultarPorSituacao(SituacaoCaderneta.CADERNETA_COM_COORDENADOR);
		Map<String, IMessage> relatorioEmailsEnviados = new HashMap<String, IMessage>();

		for (Caderneta caderneta : cadernetasComCoordenador) {
			boolean enviarEmail = isEnviarEmailHoje(hoje, caderneta.getDataFinalDisciplina(), caderneta.getDataPrevistaDevolucaoCoordenador(), Integer.valueOf(parametro.getIntervaloDiasParaEnvioEmail()));
			if (enviarEmail) {
				//envia email para o professor solicitando devolucao da caderneta
				TemplateEmail templateComMerge = CadernetaUtil.mergeTemplateSolicitarDevolucaoCaderneta(parametro.getSolicitarDevolucaoCaderneta(), caderneta, false);
//				EmailUtil.enviarEmail(parametro, templateComMerge, relatorioEmailsEnviados, caderneta.getCoordenador().getEmail());
			}

			enviarEmail = isEnviarEmailComPrazoExtrapolado(hoje, caderneta.getDataPrevistaDevolucaoCoordenador());
			if (enviarEmail) {
				//envia email para o coordenador pra devolver a caderneta, o prazo ja esta extrapolado e o email continuara enviando
				TemplateEmail templateComMerge = CadernetaUtil.mergeTemplateSolicitarDevolucaoCaderneta(parametro.getSolicitarDevolucaoCaderneta(), caderneta, false);
//				EmailUtil.enviarEmail(parametro, templateComMerge, relatorioEmailsEnviados, caderneta.getCoordenador().getEmail());
			}
		}
		
		//Somente envia o relatorio se houver mensagens. Evitar de enviar e-mails de relatorios vazios.
		if (!relatorioEmailsEnviados.isEmpty()) {
			enviarEmailRelatorioBatch(relatorioEmailsEnviados, parametro, "Relatorio de envio de e-mails para 'Coordenador com Caderneta'");
		}
	}

	private void enviarEmailRelatorioBatch(Map<String,IMessage> mensagens, ParametroSistema parametroSistema, String assunto){
		StringBuilder msg = new StringBuilder();
		for (String email : mensagens.keySet()) {
			IMessage mensagem = mensagens.get(email);
			if(mensagem.getSeverity().equals(FacesMessage.SEVERITY_ERROR)){
				msg.append("Falha ao enviar email: "+email);
				msg.append("<br/>");
				System.out.println("Falha ao enviar email: "+email);
			}else{
				System.out.println("Email enviado com sucesso para o e-mail: "+email);
				msg.append("Email enviado com sucesso para o e-mail: "+email);
				msg.append("<br/>");
			}
		}
		
//		EmailUtil.enviarEmail(parametroSistema, assunto, msg.toString() ,parametroSistema.getEmail());
	}
	
	/**
	 * Este m�todo informa se hoje � dia de mandar e-mail ou n�o baseado nos par�metros informados.
	 */
	public boolean isEnviarEmailHoje(LocalDate hoje, LocalDate dataInicial, LocalDate dataFinal, Integer intervalo) {
		if (intervalo == 1) {
			//para intervalos de 1 dia, envia email todo dia
			return true;
		}

		//so comeca a calcular o envio de emails, quando a data de hoje for igual ou maior que a data final da disciplina
		if (hoje.isBefore(dataInicial)) {
			return false;
		}

		//extrapolou!!!
		if (hoje.isAfter(dataFinal)) {
			return false;
		}
		
		LocalDate dataInicio = dataInicial;
		do {
			dataInicio = dataInicio.plusDays(intervalo);
			if (hoje.equals(dataInicio)) {
				return true;
			}
		} while (dataInicio.isBefore(dataFinal));

		return false;
	}
	
	public boolean isEnviarEmailComPrazoExtrapolado(LocalDate hoje, LocalDate dataFinal) {
		if (hoje.isAfter(dataFinal)) {
			return true;
		}
		return false;
	}
	/**
	 * Atribui o valor do parâmetro parametroSistemaService para o campo parametroSistemaService.
	 * @param valor para o campo parametroSistemaService.
	 */
	public void setParametroSistemaService(
			ParametroSistemaService parametroSistemaService) {
		this.parametroSistemaService = parametroSistemaService;
	}

	public CadernetasService getCadernetasService() {
		return cadernetasService;
	}

	public void setCadernetasService(CadernetasService cadernetasService) {
		this.cadernetasService = cadernetasService;
	}

}
