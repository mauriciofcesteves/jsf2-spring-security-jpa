package infraestrutura.exception;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public enum WarningMessage implements IMessage {

	DESEJA_EXCLUIR("deseja_excluir"),
	EMAIL_NAO_ENVIADO("email_nao_enviado");

	private String key;
	
	private WarningMessage(String key) {
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
		return FacesMessage.SEVERITY_WARN;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getMensagem() {
		ResourceBundle bundle = ResourceBundle.getBundle(IMessage.BUNDLE_NAME);
		return bundle.getString(key);
	}
}
