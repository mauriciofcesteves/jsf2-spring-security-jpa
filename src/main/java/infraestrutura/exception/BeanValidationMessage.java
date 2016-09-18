package infraestrutura.exception;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public enum BeanValidationMessage implements IMessage {

	NOT_EMPTY("org.hibernate.validator.constraints.NotEmpty.message"); //

	private String key;
	
	private BeanValidationMessage(String key) {
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
		return FacesMessage.SEVERITY_ERROR;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getMensagem() {
		ResourceBundle bundle = ResourceBundle.getBundle(IMessage.BUNDLE_NAME);
		return bundle.getString(key);
	}
}
