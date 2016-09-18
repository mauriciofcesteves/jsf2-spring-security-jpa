package infraestrutura.util;

import infraestrutura.model.ParametroSistema;
import infraestrutura.model.TemplateEmail;

public class ParametroSistemaUtilTest {

	public static ParametroSistema criar(Integer id) {
		ParametroSistema parametro = new ParametroSistema();
		parametro.setId(id);
		parametro.setAtivo(true);
		parametro.setEmailGOP("mauricio.fc.esteves@gmail.com");
		parametro.setEmail("noreply@pos.shservidores19.com.br");
		parametro.setPortaSmtp("587");
		parametro.setSmtp("mail.pos.shservidores19.com.br");
		parametro.setProtocolo(null);
		parametro.setSenha("#noreply_estacio#");
		parametro.setIntervaloDiasParaEnvioEmail("2");
		parametro.setPrazoCoordenador("15");
		parametro.setPrazoProfessor("15");
		parametro.setInformarGestorOperacional(criarInformarGestorOperacional());
		parametro.setSolicitarDevolucaoCaderneta(criarSolicitarDevolucaoCaderneta());
		parametro.setInformarPrazoExtrapolado(criarInformarPrazoExtrapolado());
		parametro.setInformarProtocoloCaderneta(criarInformarProtocoloCaderneta());
		parametro.setInformarCoordenadorCadernetaDisponivel(criarInformarCoordenadorCadernetaDisponivel());
		return parametro;
	}

	private static TemplateEmail criarInformarProtocoloCaderneta() {
		TemplateEmail email = new TemplateEmail();
		email.setAssunto("#TIPO");
		email.setCorpo("#NOME, #DISCIPLINA, #PROTOCOLO, #TIPO, #DATA_FINAL_DISCIPLINA, #DATA_PREVISTA, #DATA_RETIRADA");
		return email;
	}

	private static TemplateEmail criarSolicitarDevolucaoCaderneta() {
		TemplateEmail email = new TemplateEmail();
		email.setAssunto("#NOME");
		email.setCorpo("#NOME, #DISCIPLINA, #DATA_FINAL_DISCIPLINA, #DATA_PREVISTA, #DATA_RETIRADA");
		return email;
	}
	
	private static TemplateEmail criarInformarPrazoExtrapolado() {
		TemplateEmail email = new TemplateEmail();
		email.setAssunto("#COORDENADOR");
		email.setCorpo("#COORDENADOR, #PROFESSOR, #DISCIPLINA, #DATA_FINAL_DISCIPLINA, #DATA_PREVISTA, #DATA_HOJE, #DATA_RETIRADA");
		return email;
	}
	
	private static TemplateEmail criarInformarCoordenadorCadernetaDisponivel() {
		TemplateEmail email = new TemplateEmail();
		email.setAssunto("#COORDENADOR");
		email.setCorpo("#COORDENADOR, #PROFESSOR, #DISCIPLINA, #DATA_PREVISTA");
		return email;
	}

	private static TemplateEmail criarInformarGestorOperacional() {
		TemplateEmail email = new TemplateEmail();
		email.setAssunto("#COORDENADOR");
		email.setCorpo("#COORDENADOR, #DISCIPLINA");
		return email;
	}
}
