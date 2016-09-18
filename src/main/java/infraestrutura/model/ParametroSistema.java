package infraestrutura.model;

import infraestrutura.model.infra.Pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

/**
 * Parametros do sistema. 
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class ParametroSistema extends Pojo{

	private static final long serialVersionUID = 1L;
	
	public static final int PARAMETRO_SISTEMA_ID = 1;
	
	public static String PRAZO_PROFESSOR_DEFAULT = "15";
	public static String PRAZO_COORDENADOR_DEFAULT = "15";
	public static String INTERVALO_ENVIO_EMAILS_DEFAULT = "15";

	@Column(length=100)
	private String email;
	
	@Column(length=50)
	private String senha;
	
	@Column(length=50)
	private String smtp;
	
	@Column(length=5)
	private String portaSmtp;
	
	@Column(length=10)
	private String protocolo;
	
	@Column(length=2)
	private String prazoProfessor;
	
	@Column(length=2)
	private String prazoCoordenador;
	
	@Column(length=2)
	private String intervaloDiasParaEnvioEmail;
	
	@Column(length=100)
	private String emailGOP;
	
	@OneToOne(cascade=CascadeType.MERGE)
	private TemplateEmail informarProtocoloCaderneta;
	
	@OneToOne(cascade=CascadeType.MERGE)
	private TemplateEmail solicitarDevolucaoCaderneta;
	
	@OneToOne(cascade=CascadeType.MERGE)
	private TemplateEmail informarPrazoExtrapolado;
	
	@OneToOne(cascade=CascadeType.MERGE)
	private TemplateEmail informarCoordenadorCadernetaDisponivel;
	
	@OneToOne(cascade=CascadeType.MERGE)
	private TemplateEmail informarGestorOperacional;

	public ParametroSistema() {
		informarProtocoloCaderneta = new TemplateEmail();
		solicitarDevolucaoCaderneta = new TemplateEmail();
		informarPrazoExtrapolado = new TemplateEmail();
		informarCoordenadorCadernetaDisponivel = new TemplateEmail();
		informarGestorOperacional = new TemplateEmail();
		prazoProfessor = PRAZO_PROFESSOR_DEFAULT;
		prazoCoordenador = PRAZO_COORDENADOR_DEFAULT;
		intervaloDiasParaEnvioEmail = INTERVALO_ENVIO_EMAILS_DEFAULT;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the smtp
	 */
	public String getSmtp() {
		return smtp;
	}

	/**
	 * @param smtp the smtp to set
	 */
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	/**
	 * @return the portaSmtp
	 */
	public String getPortaSmtp() {
		return portaSmtp;
	}

	/**
	 * @param portaSmtp the portaSmtp to set
	 */
	public void setPortaSmtp(String portaSmtp) {
		this.portaSmtp = portaSmtp;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getPrazoProfessor() {
		return prazoProfessor;
	}

	public void setPrazoProfessor(String prazoProfessor) {
		this.prazoProfessor = prazoProfessor;
	}

	public String getPrazoCoordenador() {
		return prazoCoordenador;
	}

	public void setPrazoCoordenador(String prazoCoordenador) {
		this.prazoCoordenador = prazoCoordenador;
	}

	public String getIntervaloDiasParaEnvioEmail() {
		return intervaloDiasParaEnvioEmail;
	}

	public void setIntervaloDiasParaEnvioEmail(String intervaloDiasParaEnvioEmail) {
		this.intervaloDiasParaEnvioEmail = intervaloDiasParaEnvioEmail;
	}

	public TemplateEmail getInformarProtocoloCaderneta() {
		return informarProtocoloCaderneta;
	}

	public void setInformarProtocoloCaderneta(
			TemplateEmail informarProtocoloCaderneta) {
		this.informarProtocoloCaderneta = informarProtocoloCaderneta;
	}

	public TemplateEmail getSolicitarDevolucaoCaderneta() {
		return solicitarDevolucaoCaderneta;
	}

	public void setSolicitarDevolucaoCaderneta(
			TemplateEmail solicitarDevolucaoCaderneta) {
		this.solicitarDevolucaoCaderneta = solicitarDevolucaoCaderneta;
	}

	public TemplateEmail getInformarPrazoExtrapolado() {
		return informarPrazoExtrapolado;
	}

	public void setInformarPrazoExtrapolado(TemplateEmail informarPrazoExtrapolado) {
		this.informarPrazoExtrapolado = informarPrazoExtrapolado;
	}

	public TemplateEmail getInformarCoordenadorCadernetaDisponivel() {
		return informarCoordenadorCadernetaDisponivel;
	}

	public void setInformarCoordenadorCadernetaDisponivel(
			TemplateEmail informarCoordenadorCadernetaDisponivel) {
		this.informarCoordenadorCadernetaDisponivel = informarCoordenadorCadernetaDisponivel;
	}

	public TemplateEmail getInformarGestorOperacional() {
		return informarGestorOperacional;
	}

	public void setInformarGestorOperacional(TemplateEmail informarGestorOperacional) {
		this.informarGestorOperacional = informarGestorOperacional;
	}

	public String getEmailGOP() {
		return emailGOP;
	}

	public void setEmailGOP(String emailGOP) {
		this.emailGOP = emailGOP;
	}

}
