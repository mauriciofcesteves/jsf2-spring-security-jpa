package infraestrutura.exception;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public enum InfoMessage implements IMessage {

	OPERACAO_SUCESSO("operacao_realizada_com_sucesso"),
	EMAIL_SUCESSO("email_sucesso"),
	EMAIL_INFORMADO_SUCESSO("email_informado_sucesso");

	private String key;
	
	private InfoMessage(String key) {
		this.key = key;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getKey() {
		return key;
	}

	/**
	 * {@inheritDoc}
	 */
	public Severity getSeverity() {
		return FacesMessage.SEVERITY_INFO;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getMensagem() {
		ResourceBundle bundle = ResourceBundle.getBundle(IMessage.BUNDLE_NAME);
		return bundle.getString(key);
	}

}
